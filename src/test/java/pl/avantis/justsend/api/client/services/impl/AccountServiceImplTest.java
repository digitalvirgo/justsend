package pl.avantis.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.model.Account;
import pl.avantis.justsend.api.client.model.SubAccount;
import pl.avantis.justsend.api.client.model.SubAccountRaw;
import pl.avantis.justsend.api.client.services.impl.enums.UserStatus;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.avantis.justsend.api.client.test.helpers.DataGenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.LogManager;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.checkIfProdUrl;
import static pl.avantis.justsend.api.client.services.impl.enums.AccountType.SLAVE;
import static pl.avantis.justsend.api.client.test.helpers.DataGenerator.generateDigitNumber;
import static pl.avantis.justsend.api.client.test.helpers.DataGenerator.getRandomEmail;

public class AccountServiceImplTest {

    private AccountServiceImpl accountService;
    private Logger LOGGER = Logger.getLogger(AccountServiceImplTest.class);

    @BeforeClass(alwaysRun = true)
    protected void setUp() throws SecurityException, IOException {
        Constants.JUSTSEND_API_URL="http://justsend-api.dcos.staging.avantis.pl/api/rest";
        accountService = new AccountServiceImpl(APP_KEY);
        checkIfProdUrl();
        FileInputStream fis = new FileInputStream(AccountServiceImplTest.class.getResource("/log4j.xml").getFile());
        LogManager.getLogManager().readConfiguration(fis);
    }

    //Not work

    @Test
    public void accountCreationAndDeactivationTest() throws JustsendApiClientException {
        LOGGER.info("============== create sub Account ============");
        List<SubAccount> subAccountListBeforeNewAccountCreation = accountService.retrieveSubAccountsList();

        String email = getRandomEmail();
        SubAccountRaw subAccountRaw = new SubAccountRaw(email, "12345678", "Test", "API", "", 100);
        SubAccount subAccountResponse = accountService.createSubAccount(subAccountRaw);
        assertThat(subAccountResponse.getEmail()).isEqualTo(email);

        List<SubAccount> subAccountListAfterNewAccountCreation = accountService.retrieveSubAccountsList();
        Assert.assertEquals(subAccountListBeforeNewAccountCreation.size() + 1, subAccountListAfterNewAccountCreation.size());


        LOGGER.info("==============  deactivate account ============");
        //when
        String deactivateAccount = accountService.deactivateAccount(subAccountResponse.getAppKey());

        //then
        assertThat(deactivateAccount).isEqualTo(format("Slave with id : %s was deactivated", subAccountResponse.getSubid()));
        List<SubAccount> subAccountListAfterDeactivation = accountService.retrieveSubAccountsList();
        assertThat(subAccountListAfterDeactivation)
                .filteredOn("subid", subAccountResponse.getSubid())
                .allMatch((subAccount -> subAccount.getUserStatus().equals(UserStatus.NOT_ACTIVE)));
    }

    @Test
    public void testEditSubAccount() throws JustsendApiClientException {
        //given
        String email = getRandomEmail();
        SubAccountRaw subAccountRaw = new SubAccountRaw(email, "12345678", "Test", "API", "", 100);
        SubAccount subAccount = accountService.createSubAccount(subAccountRaw);

        String newFirstName = subAccount.getContactFirstname() + generateDigitNumber(4);
        String newContactSurname = subAccount.getContactSurname() + generateDigitNumber(4);
        String newDescription = subAccount.getDescription() + generateDigitNumber(4);

        //when
        SubAccount subAccountAfterEdit = accountService.editSubAccount(subAccount.getSubid(), newFirstName, newContactSurname, subAccount.getPassword(), newDescription);

        //then
        assertThat(subAccountAfterEdit.getContactFirstname()).isEqualTo(newFirstName);
        assertThat(subAccountAfterEdit.getContactSurname()).isEqualTo(newContactSurname);
        assertThat(subAccountAfterEdit.getDescription()).isEqualTo(newDescription);
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
        SubAccount subAccountGiven = getSubAccount(subAccountList);

        //when
        SubAccount subAccountRetrieved = accountService.retrieveSubAccount(subAccountGiven.getAppKey());

        //then
        assertThat(subAccountRetrieved.getContactFirstname()).isEqualTo(subAccountGiven.getContactFirstname());
    }

    private SubAccount getSubAccount(List<SubAccount> subAccountList) {
        Optional<SubAccount> first = subAccountList.stream().filter(subAccount -> subAccount.getAccountType() == SLAVE).findFirst();
        return first.get();
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
