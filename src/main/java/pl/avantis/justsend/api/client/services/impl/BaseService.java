package pl.avantis.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.services.impl.http.JustsendHttpClient;
import pl.avantis.justsend.api.client.services.impl.http.utils.JSONSerializer;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Properties;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;


public abstract class BaseService {

    protected static final Logger LOGGER = Logger.getLogger(BaseService.class);

    protected Properties properties;
    protected String JUSTSEND_API_URL;

    //TODO add production URL
    protected JustsendHttpClient justsendHttpClient;

    /**
     * @param appKey Klucz api
     */

    public BaseService(String appKey) {
        requireNonNull(appKey);
        justsendHttpClient = new JustsendHttpClient(appKey);
        initProperties();
    }

    public void initProperties() {

        if (nonNull(properties) && !properties.isEmpty()) {
            return;
        }
        InputStream inputStream = BaseService.class.getClassLoader().getResourceAsStream("config/config.properties");

        properties = new Properties();
        try {
            properties.load(inputStream);
            LOGGER.debug("Initialized properties.");
            LOGGER.debug(properties.toString());
            JUSTSEND_API_URL = properties.getProperty("justsend.api.url");
        } catch (IOException e) {
            new IllegalStateException("Problem with initializing properties.");
        }

    }

    protected void validateResponse(JSResponse jsResponse) throws JustsendApiClientException {

        if (jsResponse.getErrorId() != 0) {
            LOGGER.error("Wrong request: " + jsResponse);
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
        return JUSTSEND_API_URL + methodPath;
    }

    protected String createURL(String methodPath, String... pathVar) throws JustsendApiClientException {

        String url = methodPath;

        if (pathVar.length > 0) {

            if (pathVar.length % 2 != 0) {
                throw new JustsendApiClientException("Incorrect parameters count!");
            }

            for (int i = 0; i < pathVar.length; i += 2) {
                LOGGER.debug("pathVar:" + i / 2 + ": " + pathVar[i] + " = " + pathVar[i + 1]);
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
            LOGGER.debug(format("param: %s : %s = %s ", i / 2, param[i], param[i + 1]));
            parameters.append(param[i]).append("=").append(param[i + 1]);
            if (i != (param.length - 2)) {
                parameters.append("&");
            }
        }
        return url.concat(parameters.toString());
    }

    public static void main(String[] args) throws JustsendApiClientException {
        BulkServiceImpl bulkService = new BulkServiceImpl("JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP");
        Bulk o = new Bulk();
        bulkService.sendBulk(o);
    }
}
