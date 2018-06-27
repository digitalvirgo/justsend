package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.Account;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.model.SubAccountRaw;
import pl.justsend.api.client.services.AccountService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.services.impl.TestHelper.APP_KEY;

public class AccountServiceImplTest {

    private AccountService accountService;
    private Logger LOGGER = Logger.getLogger(AccountServiceImplTest.class);

    @BeforeClass
    public void init() {
        accountService = new AccountServiceImpl(APP_KEY);
    }

    @Test
    public void accountCreationAndDeactivationTest() throws JustsendApiClientException {
        LOGGER.info("============== create sub Account ============");
        List<SubAccount> subAccountListBeforeNewAccountCreation = accountService.retrieveSubAccountsList();

        String email = UUID.randomUUID() + "@justsendapiclient.pl";
        SubAccountRaw subAccountRaw = new SubAccountRaw(email, "12345678", "Test", "API", "", 100);
        SubAccount subAccount = accountService.createSubAccount(subAccountRaw);
        assertThat(subAccount.getEmail()).isEqualTo(email);

        List<SubAccount> subAccountListAfterNewAccountCreation = accountService.retrieveSubAccountsList();
        Assert.assertEquals(subAccountListBeforeNewAccountCreation.size() + 1, subAccountListAfterNewAccountCreation.size());


        LOGGER.info("==============  deactivate account ============");
        List<SubAccount> subAccountList = accountService.retrieveSubAccountsList();
        int index = subAccountList.size() - 1;
        String deactivateAccount = accountService.deactivateAccount(subAccountList.get(index).getAppKey());
        assertThat(deactivateAccount).isEqualTo(format("Slave with id : %s was deactivated", subAccountList.get(index).getSubid()));
        List<SubAccount> subAccountListAfterDeactivation = accountService.retrieveSubAccountsList();
        // TODO: metoda nie deaktywuje konta, cos nie dziala w aplikacji
        //        assertThat(subAccountListAfterDeactivation).filteredOn("subid",subAccountList.get(index).getSubid()).isEqualTo(UserStatus.NOT_ACTIVE);
    }

    //TODO: not used, maybe disable it
    @Test(enabled = false)
    public void testEditSubAccount() throws JustsendApiClientException {
        //get: JustsendApiClientException: INTERNAL_ERROR - Internal system error, no information in logs
        //given
        String email = UUID.randomUUID() + "@justsendapiclient.pl";
        SubAccountRaw subAccountRaw = new SubAccountRaw(email, "12345678", "Test", "API", "", 100);
        SubAccount subAccount = accountService.createSubAccount(subAccountRaw);

        String newFirstName = subAccount.getContactFirstname() + "1234";
        //when
        SubAccount subAccountAfterEdit = accountService.editSubAccount(subAccount.getSubid(), newFirstName, subAccount.getContactSurname(), subAccount.getPassword(), subAccount.getDescription());

        //then
        Assert.assertNotEquals(newFirstName, subAccountAfterEdit.getContactFirstname());
    }

    @Test
    public void retrieveAccountTest() throws JustsendApiClientException {
        //when
        Account account = accountService.retrieveAccount();

        //then
        assertThat(account.getAppKey()).isEqualTo(APP_KEY);
    }

    @Test
    public void createSubAccountTest() throws JustsendApiClientException {
        //given
        List<SubAccount> subAccountListBeforeNewAccountCreation = accountService.retrieveSubAccountsList();
        SubAccountRaw subAccountRaw = createRequestCreateSubAccount();

        //when

        SubAccount subAccountResponse = accountService.createSubAccount(subAccountRaw);

        //then
        Assertions.assertThat(subAccountResponse.getEmail()).isEqualTo(subAccountRaw.getEmail());
        List<SubAccount> subAccountListAfterNewAccountCreation = accountService.retrieveSubAccountsList();
        Assert.assertEquals(subAccountListBeforeNewAccountCreation.size() + 1, subAccountListAfterNewAccountCreation.size());
    }

    @Test
    public void resetSubAccountTest() throws JustsendApiClientException {
        //given
        SubAccount subAccountResponse = accountService.createSubAccount(createRequestCreateSubAccount());

        //when
        String resetSubAccount = accountService.resetSubAccount(subAccountResponse.getSubid());

        //then
        Assertions.assertThat(resetSubAccount).isEqualTo(format("Slave appKey with id %s has been reset", subAccountResponse.getSubid()));
    }

    @Test
    public void retrieveSubAccount() throws JustsendApiClientException {
        //given
        List<SubAccount> subAccountList = accountService.retrieveSubAccountsList();

        //when
        SubAccount subAccount = accountService.retrieveSubAccount(subAccountList.get(2).getAppKey());

        //then
        Assert.assertEquals(subAccount.getContactFirstname(), subAccount.getContactFirstname());
    }

    @Test(dependsOnMethods = "createSubAccountTest")
    public void retrieveSubAccountsListTest() throws JustsendApiClientException {
        List<SubAccount> subAccountList = accountService.retrieveSubAccountsList();
        Assert.assertNotNull(subAccountList);
    }

    @Test
    public void testRetrieveAccount() throws JustsendApiClientException {
        Account account = accountService.retrieveAccount();
        assertThat(account.getAppKey()).isEqualTo(APP_KEY);
    }

    private SubAccountRaw createRequestCreateSubAccount() {
        return new SubAccountRaw(UUID.randomUUID() + "@justsendapiclient.pl", "12345678", "Test", "API", "", 100);
    }
}
