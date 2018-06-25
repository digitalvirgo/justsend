package pl.justsend.api.client.services.impl;

import pl.justsend.api.client.services.AccountService;
import pl.justsend.api.client.services.BlackListService;
import pl.justsend.api.client.services.MessageService;

/**

 * User: posiadacz
 * Date: 30.03.18
 * Time: 13:18
 */
public abstract class BaseTest {

    public static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";
    public static final String APP_KEY_ADMINISTRATOR = "123456";


    protected AccountService accountService;
    protected MessageService messageService;
    protected BlackListService blackListService;

}
