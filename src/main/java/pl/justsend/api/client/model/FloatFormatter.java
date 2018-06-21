package pl.justsend.api.client.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**

 * User: tmajewski
 * Date: 06.06.14
 * Time: 14:57
 */
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
