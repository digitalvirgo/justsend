package pl.justsend.api.client.test.services;

import org.apache.log4j.Logger;
import pl.justsend.api.client.services.*;

/**

 * User: posiadacz
 * Date: 30.03.18
 * Time: 13:18
 */
public abstract class BaseTest {

    protected static final Logger logger = Logger.getLogger(AccountServiceTest.class);
    public static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";

    protected AccountService accountService;
    protected MessageService messageService;
    protected BlackListService blackListService;
    protected BulkService bulkService;
    protected VoiceBulkService voiceBulkService;

}
