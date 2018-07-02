package pl.avantis.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import pl.avantis.justsend.api.client.services.impl.enums.FileNamePartEnum;

import java.io.File;
import java.time.LocalDate;

public class TestHelper {

    public static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";
    public static final String APP_KEY_ADMINISTRATOR = "123456";
    public static final Long GROUP_ID = 2393L;

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

    public File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(fileName).getFile();
        return new File(file);
    }
}