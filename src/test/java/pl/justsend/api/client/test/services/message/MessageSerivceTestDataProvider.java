package pl.justsend.api.client.test.services.message;

import org.testng.annotations.DataProvider;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.test.services.BaseTest;

/**

 * User: posiadacz
 * Date: 30.03.18
 * Time: 13:26
 */
public class MessageSerivceTestDataProvider extends BaseTest {

    @DataProvider()
    protected static Object[][] sendMessageTestDataProvider() {
        return new Object[][] {
                {"48505948385", "TEST", "Justsend lib api test", BulkVariant.ECO},
                {"48505948385", "TEST", "Justsend lib api test", BulkVariant.PRO_RESP},
                {"505948385", "TEST", "Justsend lib api test", BulkVariant.ECO},
        };
    }

    @DataProvider()
    protected static Object[][] sendMessageIncorrectMSISDNDataProvider() {
        return new Object[][] {
                {"48505948385123", "TEST", "Justsend lib api test", BulkVariant.ECO},
        };
    }

    @DataProvider()
    public static Object[][] sendMessageIncorrectSenderDataProvider() {
        return new Object[][] {
                {"48505948385", "A", "Justsend lib api test", BulkVariant.PRO},
                {"48505948385", "A", "Justsend lib api test", BulkVariant.PRO_RESP},
                {"48505948385", "A", "Justsend lib api test", BulkVariant.FULL},
                {"48505948385", "1234", "Justsend lib api test", BulkVariant.PRO},
                {"48505948385", "1234", "Justsend lib api test", BulkVariant.PRO_RESP},
                {"48505948385", "1234", "Justsend lib api test", BulkVariant.FULL},
        };
    }

}
