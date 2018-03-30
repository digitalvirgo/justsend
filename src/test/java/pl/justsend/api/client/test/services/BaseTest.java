package pl.justsend.api.client.test.services;

import com.sun.deploy.util.BlackList;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.services.BlackListService;
import pl.justsend.api.client.services.MessageService;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 30.03.18
 * Time: 13:18
 */
public abstract class BaseTest {

    protected static final Logger logger = Logger.getLogger(AccountServiceTest.class);
    protected static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";

    protected MessageService messageService;
    protected BlackListService blackListService;

}
