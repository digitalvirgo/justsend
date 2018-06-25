package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;

public class TestHelper {

    public File getFile(String multiPartFileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(multiPartFileName).getFile();
        return new File(file);
    }


    public static ReflectionToStringBuilder toString(Object object) {
        return new ReflectionToStringBuilder(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
