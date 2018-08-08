package pl.avantis.justsend.api.client.test.helpers;

import pl.avantis.justsend.api.client.model.GroupMember;

import java.util.Random;
import java.util.UUID;

import static java.lang.Math.pow;
import static java.lang.String.valueOf;

public class DataGenerator {

    private final static Random random = new Random();

    public static String getRandomPhoneNumber() {
        return "48514875" + valueOf(generateDigitNumber(3));
    }

    public static GroupMember getRandomPhoneNumberMember() {
        return new GroupMember("48514875" + valueOf(generateDigitNumber(3)));
    }

    public static String getIncorrectPhoneNumber() {
        return valueOf(random.nextInt(1000000));
    }

    public static int generateDigitNumber(int size) {
        return random.nextInt(9 * (int) pow(10, size - 1)) + (int) pow(10, size - 1);
    }

    public static String getRandomEmail() {
        return UUID.randomUUID() + "@justsendapiclient.pl";
    }


}
