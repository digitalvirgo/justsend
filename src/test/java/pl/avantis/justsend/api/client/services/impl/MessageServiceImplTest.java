package pl.avantis.justsend.api.client.services.impl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.model.Message;
import pl.avantis.justsend.api.client.model.VoiceMessage;
import pl.avantis.justsend.api.client.services.impl.enums.BulkVariant;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.model.LanguageMessage.POLISH;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.checkIfProdUrl;

public class MessageServiceImplTest extends MessageSerivceTestDataProvider {

    protected MessageServiceImpl messageService;

    @BeforeClass
    public void init() {
        messageService = new MessageServiceImpl(APP_KEY);
        checkIfProdUrl(messageService);
    }

    /* sendMessage - post */
    @Test(dataProvider = "sendMessageTestDataProvider")
    public void sendMessageTestPOST(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Message message = new Message(to, from, text, bulkVariant);
        Long messageId = messageService.sendMessage(message);
        assertThat(messageId).isPositive();
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
    public void sendMessageTestGET(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Long messageId = messageService.sendMessage(to, from, text, bulkVariant);
        assertThat(messageId).isPositive();
    }

    @Test(dataProvider = "sendMessageIncorrectMSISDNDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void sendMessageIncorrectMsisdnTestGET(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        messageService.sendMessage(to, from, text, bulkVariant);
    }

    @Test(dataProvider = "sendMessageIncorrectSenderDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void sendMessageIncorrectSenderTestGET(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        messageService.sendMessage(to, from, text, bulkVariant);
    }


    /* sendMessageEco */
    @Test
    public void sendMessageECOTest() throws JustsendApiClientException {
        Long messageId = messageService.sendMessageECO("505948385", "Test ECO sendMessageAPI");
        assertThat(messageId).isPositive();
    }

    @Test(dataProvider = "sendMessageIncorrectMSISDNDataProvider", expectedExceptions = JustsendApiClientException.class)
    public void sendMessageIncorrectMSIDNECOTest(String to, String from, String text, BulkVariant bulkVariant) throws JustsendApiClientException {
        Long messageId = messageService.sendMessageECO(to, text);
        assertThat(messageId).isPositive();
    }

    @Test
    public void testSendVoiceMessage() throws JustsendApiClientException {
        Long messageId = messageService.sendVoiceMessage(createVoiceMessageRequest());
        assertThat(messageId).isPositive();
    }

    private VoiceMessage createVoiceMessageRequest() {
        VoiceMessage voiceMessage = new VoiceMessage();
        voiceMessage.setMessage("Wiadomość testowa.");
        voiceMessage.setLanguageMessage(POLISH);
        voiceMessage.setFrom("Test User");
        voiceMessage.setTo("514763829");
        return voiceMessage;
    }
}
