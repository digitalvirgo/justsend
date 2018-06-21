package pl.justsend.api.client.test.services.message;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.Message;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.services.exception.JustsendApiClientException;
import pl.justsend.api.client.services.impl.MessageServiceImpl;

/**

 * User: posiadacz
 * Date: 29.03.18
 * Time: 13:42
 */
public class MessageServiceTest extends MessageSerivceTestDataProvider {

    @BeforeClass
    public void init() {
        messageService = new MessageServiceImpl(APP_KEY);
    }

    /* sendMessage - post */
    @Test(dataProvider = "sendMessageTestDataProvider")
    public void sendMessageTestPOST(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Message message = new Message(to, from, text, bulkVariant);
        Long messageId = messageService.sendMessage(message);
        Assert.assertEquals(messageId.getClass(), Long.class);
    }

    @Test(dataProvider = "sendMessageIncorrectMSISDNDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void sendMessageIncorrectMsisdnTestPOST(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Message message = new Message(to, from, text, bulkVariant);
        messageService.sendMessage(message);
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
