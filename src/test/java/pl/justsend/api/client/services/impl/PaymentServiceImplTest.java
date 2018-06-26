package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.model.SubAccountRaw;
import pl.justsend.api.client.model.UserPurseResponse;
import pl.justsend.api.client.services.AccountService;
import pl.justsend.api.client.services.PaymentService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.UUID;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.services.impl.TestHelper.APP_KEY_ADMINISTRATOR;

public class PaymentServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImplTest.class);

    private PaymentService paymentService;
    private AccountService accountService;
    private Long points = 100L;
    private SubAccount subAccountResponse;

    @BeforeClass
    public void setUp() throws JustsendApiClientException {
        accountService = new AccountServiceImpl(APP_KEY_ADMINISTRATOR);

        subAccountResponse = accountService.createSubAccount(createSubAccountRequest(points));
        LOGGER.info("subAccountResponse = " + TestHelper.toString(subAccountResponse));
    }

    private SubAccountRaw createSubAccountRequest(Long points) {
        return new SubAccountRaw(UUID.randomUUID() + "@justsendapiclient.pl", "12345678", "Test", "API", "", points.intValue());
    }

    @Test
    public void testRetrieveCountPoints() throws JustsendApiClientException {
        //given
        paymentService = new PaymentServiceImpl(APP_KEY_ADMINISTRATOR);

        //when
        Long retrieveCountPoints = paymentService.retrieveCountPoints();

        //then
        assertThat(retrieveCountPoints).isNotNegative();
    }

    @Test
    public void testCheckBalancePointsUser() throws JustsendApiClientException {
        //given
        paymentService = new PaymentServiceImpl(subAccountResponse.getAppKey());

        //when
        UserPurseResponse userPurseResponse = paymentService.checkBalancePointsUser();

        //then
        assertThat(userPurseResponse.getBalanceInPoints()).isEqualTo(points);
        LOGGER.info("retrieveCountPoints = " + new ReflectionToStringBuilder(userPurseResponse, ToStringStyle.SHORT_PREFIX_STYLE));
    }

    @Test(dependsOnMethods = {"testRetrieveCountPoints"})
    public void testSetBalancePointsUser() throws JustsendApiClientException {
        //given
        paymentService = new PaymentServiceImpl(APP_KEY_ADMINISTRATOR);

        //when
        String setBalancePointsUserResponse = paymentService.setBalancePointsUser(subAccountResponse.getSubid().intValue(), 2L);

        //then
        assertThat(setBalancePointsUserResponse).isEqualTo(format("Balance points for SubAccount with id %s and his Master with id %s was changed", subAccountResponse.getSubid(), subAccountResponse.getMasterId()));
    }
}