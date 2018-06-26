package pl.justsend.api.client.services.impl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.LanguageMessage;
import pl.justsend.api.client.model.Message;
import pl.justsend.api.client.model.VoiceMessage;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.services.MessageService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.model.LanguageMessage.POLISH;
import static pl.justsend.api.client.services.impl.TestHelper.APP_KEY;

public class MessageServiceImplTest extends MessageSerivceTestDataProvider {

    protected MessageService messageService;

    @BeforeClass
    public void init() {
        messageService = new MessageServiceImpl(APP_KEY);
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
