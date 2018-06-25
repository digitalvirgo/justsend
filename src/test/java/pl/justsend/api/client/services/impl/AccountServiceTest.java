package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.Account;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.model.SubAccountRaw;
import pl.justsend.api.client.services.AccountService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

/**

 * User: posiadacz
 * Date: 21.03.18
 * Time: 15:49
 */
public class AccountServiceTest {

    private static final Logger logger = Logger.getLogger(AccountServiceTest.class);
    private static final String APP_KEY = "JDJhJDEyJDN2c1NWQ2o1ZHh1U3M1WHpmYXpFN3VhRGZQSUlub3hwT3hIRzU1bkJ4MWpjbVZPaFAxcEdP";

    private AccountService accountService;

    @BeforeClass
    public void init() {
        accountService = new AccountServiceImpl(APP_KEY);
    }

    @Test
    public void retriveAccountTest() throws JustsendApiClientException {
        logger.info("retriveAccountTest..");
        Account account = accountService.retrieveAccount();
        logger.info("Account: " + account);
    }

    @Test
    public void createSubAccountTest() throws JustsendApiClientException {
        logger.info("createSubAccountTest..");

        SubAccountRaw subAccountRaw = new SubAccountRaw();
        subAccountRaw.setFirstname("Tests");
        subAccountRaw.setSurname("Testowosskis");
        subAccountRaw.setEmail("test@tessssst.pl");
        subAccountRaw.setPassword("1234251678");
        subAccountRaw.setDescription("api lib test");
        subAccountRaw.setPoints(100);

        SubAccount subAccount = accountService.createSubAccount(subAccountRaw);
        logger.info("createSubAccountTest response : " + subAccount);

    }

}
