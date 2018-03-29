package pl.justsend.api.client.test.services;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.Message;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.services.AccountService;
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

    @Test
    public void sendMessageTestPOST() throws JustsendApiClientException {
        Message message = new Message();
        message.setTo("48505948385");
        message.setFrom("TEST");
        message.setMessage("Justsend lib api test");
        message.setBulkVariant(BulkVariant.ECO);
        Long messageId = messageService.sendMessage(message);
        logger.info("got message id = " + messageId);
    }

    @Test
    public void  sendMessageTestGET() throws JustsendApiClientException {
        Long messageId = messageService.sendMessage("505948385", "TEST", "Test GET sendMessage API", BulkVariant.ECO);
        logger.info("got message id = " + messageId);
    }

}
