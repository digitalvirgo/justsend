package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestHelper {

    public static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";
    public static final String APP_KEY_ADMINISTRATOR = "123456";

    public File getFile(String multiPartFileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(multiPartFileName).getFile();
        return new File(file);
    }

    public static ReflectionToStringBuilder toString(Object object) {
        return new ReflectionToStringBuilder(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String daysBeforeNow(int minusDays) {
        LocalDate localDate = LocalDate.now().minusDays(minusDays);
        return dtf.format(localDate);
    }

    public static LocalDate daysBeforeNowLD(int minusDays) {
        return LocalDate.now().minusDays(minusDays);
    }

}
