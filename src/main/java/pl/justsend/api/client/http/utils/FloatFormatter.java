package pl.justsend.api.client.http.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FloatFormatter {

    private static final String pattern = "0.00";

    private static final DecimalFormatSymbols otherSymbols;

    static {
        otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
    }

    public static String format(Float f) {
        if (f == null) {
            return "0";
        } else {
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setDecimalFormatSymbols(otherSymbols);
            return decimalFormat.format(f);
        }
    }
}
