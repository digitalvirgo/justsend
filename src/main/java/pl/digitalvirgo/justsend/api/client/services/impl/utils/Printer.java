package pl.digitalvirgo.justsend.api.client.services.impl.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Printer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Printer.class);

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