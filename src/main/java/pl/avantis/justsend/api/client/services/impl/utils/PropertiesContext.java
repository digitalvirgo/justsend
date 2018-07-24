package pl.avantis.justsend.api.client.services.impl.utils;

import org.apache.log4j.Logger;
import pl.avantis.justsend.api.client.services.impl.BaseService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Objects.nonNull;
import static org.apache.http.util.TextUtils.isBlank;

public class PropertiesContext {

    private static final Logger LOGGER = Logger.getLogger(PropertiesContext.class);

    private static Properties properties;
    public static String propertiesPath;


    public static void init(){
        if (nonNull(properties) && !properties.isEmpty()) {
            return;
        }

        InputStream inputStream;
        if (isBlank(propertiesPath)){
            inputStream = BaseService.class.getClassLoader().getResourceAsStream("application.properties");
        } else {
            inputStream = BaseService.class.getClassLoader().getResourceAsStream(propertiesPath);
        }

        properties = new Properties();
        try {
            properties.load(inputStream);
            LOGGER.debug("Initialized properties.");
            LOGGER.debug(properties.toString());

        } catch (IOException e) {
            new IllegalStateException("Problem with initializing properties.");
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        PropertiesContext.properties = properties;
    }
}
