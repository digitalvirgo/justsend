package pl.justsend.api.client.services;

import org.apache.log4j.Logger;
import pl.justsend.api.client.http.JustsendHttpClient;
import pl.justsend.api.client.http.utils.JSONSerializer;
import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.lang.reflect.Type;


/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 21.03.18
 * Time: 16:03
 */
public abstract class BaseService {

    protected static final Logger logger = Logger.getLogger(BaseService.class);

    protected static final String JUSTSEND_API_URL = "http://justsend-api.dcos.staging.avantis.pl/api/rest";

    protected JustsendHttpClient justsendHttpClient;
    protected String appKey;

    public BaseService(String appKey) {
        this.appKey = appKey;
        justsendHttpClient = new JustsendHttpClient();
    }

    protected void validateResponse(JSResponse jsResponse) throws JustsendApiClientException {

        logger.info("validateResponse (jsResponse=" + jsResponse + ")");

        if (jsResponse.getErrorId() != 0) {
            logger.error("Wrong request: " + jsResponse);
            throw new JustsendApiClientException(jsResponse.getResponseCode().toString() + " - " + jsResponse.getMessage());
        }

    }

    protected <T> T processResponse(JSResponse jsResponse, Type tClass) throws JustsendApiClientException {
        validateResponse(jsResponse);
        if (tClass.equals(String.class))  {
            return (T) jsResponse.getAdditionalData();
        } else {
            return JSONSerializer.deserialize(jsResponse.getAdditionalData(), tClass);
        }
    }

    protected String createURL(String methodPath) {
        return JUSTSEND_API_URL + methodPath.replaceAll("\\{appKey\\}", appKey);
    }

    protected String createURL(String methodPath, String... param) throws JustsendApiClientException {

        String url = methodPath;

        if (param.length > 0) {

            if (param.length % 2 != 0) {
                throw new JustsendApiClientException("Incorrect parameters count!");
            }

            for (int i = 0; i < param.length; i+=2) {
                logger.info("param:" + i + ": " + param[i] + " " + param[i+1]);
                url = url.replaceAll("\\{" + param[i] + "\\}", param[i+1]);
            }

        }

        return createURL(url);

    }

}
