package pl.justsend.api.client.services.impl;

import java.io.File;

public class TestHelper {

    public File getFile(String multiPartFileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(multiPartFileName).getFile();
        return new File(file);
    }

}
