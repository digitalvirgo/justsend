package pl.avantis.justsend.api.client.test.helpers;

import java.util.Random;
import java.util.UUID;

import static java.lang.String.valueOf;

public class DataGenerator {

    private final static Random random = new Random();

    public static String getRandomPhoneNumber() {
        return "48514875" + valueOf(generateThreeDigitNumber());
    }


    public static String getIncoretPhoneNumber() {
        return valueOf(random.nextInt(1000000));
    }

    private static int generateThreeDigitNumber() {
        return random.nextInt(900) + 100;
    }

    public static String getRandomEmail() {
        return UUID.randomUUID() + "@justsendapiclient.pl";
    }
}
