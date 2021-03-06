package pl.digitalvirgo.justsend.api.client.services.impl.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String convertDate(LocalDate date) {
        return dtf.format(date);
    }

}
