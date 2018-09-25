package pl.digitalvirgo.justsend.api.client.services.impl;

import org.testng.annotations.DataProvider;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant;
import pl.digitalvirgo.justsend.api.client.test.helpers.BaseServiceHelper;

public class MessageSerivceTestDataProvider extends BaseServiceHelper {

    @DataProvider()
    protected static Object[][] sendMessageTestDataProvider() {
        return new Object[][]{
                {"48505948385", "TEST", "Justsend lib apiść test", BulkVariant.ECO},
                {"48505948385", "TEST", "Justsend lib apiść test", BulkVariant.PRO_RESP},
                {"505948385", "TEST", "Justsend lib apiść test", BulkVariant.ECO},
        };
    }

    @DataProvider()
    protected static Object[][] sendMessageIncorrectMSISDNDataProvider() {
        return new Object[][]{
                {"48505948385123", "TEST", "Justsend libść api test", BulkVariant.ECO},
        };
    }

    @DataProvider()
    public static Object[][] sendMessageIncorrectSenderDataProvider() {
        return new Object[][]{
                {"48505948385", "A", "Justsend lib api test", BulkVariant.PRO},
                {"48505948385", "A", "Justsend lib api test", BulkVariant.PRO_RESP},
                {"48505948385", "A", "Justsend lib api test", BulkVariant.FULL},
                {"48505948385", "1234", "Justsend lib api test", BulkVariant.PRO},
                {"48505948385", "1234", "Justsend lib api test", BulkVariant.PRO_RESP},
                {"48505948385", "1234", "Justsend lib api test", BulkVariant.FULL},
        };
    }

}
