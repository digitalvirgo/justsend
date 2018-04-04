package pl.justsend.api.client.test.services.account;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.Account;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.services.exception.JustsendApiClientException;
import pl.justsend.api.client.services.impl.AccountServiceImpl;
import pl.justsend.api.client.services.impl.BlackListServiceImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 03.04.18
 * Time: 14:36
 */
public class AccountServiceTest extends AccountServiceTestDataProvider {


    @BeforeClass
    public void init() {
        accountService = new AccountServiceImpl(APP_KEY);
    }

    @Test
    public void retrieveAccountTest() throws JustsendApiClientException {
        Account account = accountService.retrieveAccount();
        Assert.assertTrue(account != null);
    }

    @Test
    public void createSubAccountTest()  {
        /* TODO */
    }

    @Test
    public void deactivateAccountTest() throws JustsendApiClientException {
        List<SubAccount> subAccountList = accountService.retrieveSubAccountsList();
        for (SubAccount subAccount : subAccountList) {
            accountService.deactivateAccount(subAccount.getAppKey());
        }
        List<SubAccount> subAccountListAfterDeactivation = accountService.retrieveSubAccountsList();
        Assert.assertTrue(subAccountList.size() == 0 || subAccountList.size() > subAccountListAfterDeactivation.size());
    }

    @Test
    public void editSubAccountTest() throws JustsendApiClientException {
        Account account = accountService.retrieveAccount();
        accountService.editSubAccount(account.getAppKey(), account.getContactFirstname(), "justSendApiTest", account.getPassword(), account.getDescription());
        account = accountService.retrieveAccount();
        accountService.editSubAccount(account.getAppKey(), account.getContactFirstname(), "justSendApiTestConfirmation", account.getPassword(), account.getDescription());
        Account editedAccount = accountService.retrieveAccount();
        Assert.assertNotEquals(account.getSurname(), editedAccount.getSurname());
    }

    @Test(enabled = false)
    public void resetSubAccount() throws JustsendApiClientException {
        Account account = accountService.retrieveAccount();
        accountService.resetSubAccount(account.getAppKey());
    }

    @Test
    public void retrieveSubAccount() throws JustsendApiClientException {
        List<SubAccount> subAccountList = accountService.retrieveSubAccountsList();
        if (subAccountList.size() > 0) {
            SubAccount subAccount = accountService.retrieveSubAccount(subAccountList.get(2).getAppKey());
            Assert.assertEquals(subAccount.getContactFirstname(), subAccount.getContactFirstname());
        }
    }

    @Test
    public void retrieveSubAccountsListTest() throws JustsendApiClientException {
        List<SubAccount> subAccountList = accountService.retrieveSubAccountsList();
        Assert.assertTrue(accountService != null);
    }

}
