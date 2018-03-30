package pl.justsend.api.client.test.services;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.Message;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.services.MessageService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;
import pl.justsend.api.client.services.impl.MessageServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 29.03.18
 * Time: 13:42
 */
public class MessageServiceTest {

    private static final Logger logger = Logger.getLogger(AccountServiceTest.class);
    private static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";

    private MessageService messageService;

    @BeforeClass
    public void init() {
        messageService = new MessageServiceImpl(APP_KEY);
    }


    /* sendMessage - post */
    @DataProvider()
    public static Object[][] sendMessageTestDataProvider() {
        return new Object[][] {
                {"48505948385", "TEST", "Justsend lib api test", BulkVariant.ECO},
                {"48505948385", "TEST", "Justsend lib api test", BulkVariant.PRO_RESP},
                {"505948385", "TEST", "Justsend lib api test", BulkVariant.ECO},
        };
    }

    @Test(dataProvider = "sendMessageTestDataProvider")
    public void sendMessageTestPOST(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Message message = new Message(to, from, text, bulkVariant);
        Long messageId = messageService.sendMessage(message);
        Assert.assertEquals(messageId.getClass(), Long.class);
    }

    @DataProvider()
    public static Object[][] sendMessageIncorrectMSISDNDataProvider() {
        return new Object[][] {
                {"48505948385123", "TEST", "Justsend lib api test", BulkVariant.ECO},
        };
    }

    @Test(dataProvider = "sendMessageIncorrectMSISDNDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void sendMessageIncorrectMsisdnTestPOST(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Message message = new Message(to, from, text, bulkVariant);
        messageService.sendMessage(message);
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

    @Test(dataProvider = "sendMessageIncorrectSenderDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void sendMessageIncorrectSenderTestPOST(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Message message = new Message(to, from, text, bulkVariant);
        messageService.sendMessage(message);
    }


   /* sendMesage - GET */
    @Test(dataProvider = "sendMessageTestDataProvider")
    public void  sendMessageTestGET(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Long messageId = messageService.sendMessage(to, from, text, bulkVariant);
        Assert.assertEquals(messageId.getClass(), Long.class);
    }

    @Test(dataProvider = "sendMessageIncorrectMSISDNDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void  sendMessageIncorrectMsisdnTestGET(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        messageService.sendMessage(to, from, text, bulkVariant);
    }

    @Test(dataProvider = "sendMessageIncorrectSenderDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void  sendMessageIncorrectSenderTestGET(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        messageService.sendMessage(to, from, text, bulkVariant);
    }


    /* sendMessageEco */
    @Test
    public void sendMessageECOTest() throws JustsendApiClientException {
        Long messageId = messageService.sendMessageECO("505948385", "Test ECO sendMessageAPI");
        Assert.assertEquals(messageId.getClass(), Long.class);
    }

    @Test(dataProvider = "sendMessageIncorrectMSISDNDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void sendMessageIncorrectMSIDNECOTest(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Long messageId = messageService.sendMessageECO(to, text);
        Assert.assertEquals(messageId.getClass(), Long.class);
    }

}
