package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import pl.justsend.api.client.http.JustsendHttpClient;
import pl.justsend.api.client.http.utils.JSONSerializer;
import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.lang.reflect.Type;

import static java.lang.String.format;


public abstract class BaseService {

    protected static final Logger logger = Logger.getLogger(BaseService.class);

    //TODO how to nicely resolve url change test/prod
    protected static final String JUSTSEND_API_URL = "http://justsend-api.dcos.staging.avantis.pl/api/rest";

    protected JustsendHttpClient justsendHttpClient;
    protected String appKey;

    /**
     * @param appKey Klucz api
     */

    public BaseService(String appKey) {
        this.appKey = appKey;
        justsendHttpClient = new JustsendHttpClient();
    }



    protected void validateResponse(JSResponse jsResponse) throws JustsendApiClientException {

        if (jsResponse.getErrorId() != 0) {
            logger.error("Wrong request: " + jsResponse);
            throw new JustsendApiClientException(jsResponse.getResponseCode() + " - " + jsResponse.getMessage(), jsResponse);
        }

    }

    protected <T> T processResponse(JSResponse jsResponse, Type tClass) throws JustsendApiClientException {
        validateResponse(jsResponse);
        if (tClass.equals(String.class)) {
            return (T) jsResponse.getAdditionalData();
        } else {
            return JSONSerializer.deserialize(jsResponse.getAdditionalData(), tClass);
        }
    }

    protected String createURL(String methodPath) {
        return JUSTSEND_API_URL + methodPath.replaceAll("\\{appKey\\}", appKey);
    }

    protected String createURL(String methodPath, String... pathVar) throws JustsendApiClientException {

        String url = methodPath;

        if (pathVar.length > 0) {

            if (pathVar.length % 2 != 0) {
                throw new JustsendApiClientException("Incorrect parameters count!");
            }

            for (int i = 0; i < pathVar.length; i += 2) {
                logger.debug("pathVar:" + i / 2 + ": " + pathVar[i] + " = " + pathVar[i + 1]);
                if (!url.contains(pathVar[i])) {
                    throw new JustsendApiClientException("Parameter don't exist in path.");
                }

                url = url.replaceAll("\\{" + pathVar[i] + "\\}", pathVar[i + 1] == null ? "sima" : pathVar[i + 1]);
            }

        }

        return createURL(url);
    }

    protected String addParameters(String url, String... param) throws JustsendApiClientException {
        if (param.length % 2 != 0) {
            throw new JustsendApiClientException("Incorrect parameters number, should be allays two multiplayer!");
        }

        StringBuilder parameters = new StringBuilder("?");
        for (int i = 0; i < param.length; i += 2) {
            logger.debug(format("param: %s : %s = %s ", i / 2, param[i], param[i + 1]));
            parameters.append(param[i]).append("=").append(param[i + 1]);
            if (i != (param.length - 2)) {
                parameters.append("&");
            }
        }
        return url.concat(parameters.toString());
    }
}