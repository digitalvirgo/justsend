package pl.avantis.justsend.api.client.services.impl.utils;

import java.io.File;

public class FileHelper {

    public File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String file = classLoader.getResource(fileName).getFile();
        return new File(file);
    }
}
