package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.justsend.api.client.model.enums.FileNamePartEnum;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestHelper {

    public static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";
    public static final String APP_KEY_ADMINISTRATOR = "123456";

    public static ReflectionToStringBuilder toString(Object object) {
        return new ReflectionToStringBuilder(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static LocalDate daysBeforeNowLD(int minusDays) {
        return LocalDate.now().minusDays(minusDays);
    }

    public static String getFileNamePart(String fileName, FileNamePartEnum partNumber) {
        String[] split = fileName.split("_");
        return split[partNumber.getPartNumber()];
    }

    public File getFile(String multiPartFileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(multiPartFileName).getFile();
        return new File(file);
    }
}
