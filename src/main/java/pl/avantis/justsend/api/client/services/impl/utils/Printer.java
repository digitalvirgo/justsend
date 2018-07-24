package pl.avantis.justsend.api.client.services.impl.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public class Printer {

    private static final Logger LOGGER = Logger.getLogger(Printer.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getNiceFormat(String json) {
        try {
            Object object = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            LOGGER.warn("Problem with converting json. ", e);
        }
        return json;
    }
}