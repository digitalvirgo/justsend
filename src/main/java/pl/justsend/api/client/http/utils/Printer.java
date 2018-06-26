package pl.justsend.api.client.http.utils;

import static org.apache.http.util.TextUtils.isBlank;

public class Printer {

    public static String getNiceFormat(String text) {
        if (isBlank(text)) {
            return text;
        }
        return text.replace("{", "\n").replace("}", "\n");
    }
}