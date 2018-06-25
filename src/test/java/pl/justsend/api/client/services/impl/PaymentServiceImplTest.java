package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.UserPurseResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import static pl.justsend.api.client.services.impl.BaseTest.APP_KEY;

public class PaymentServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImplTest.class);

    private PaymentServiceImpl paymentService;

    @BeforeMethod
    public void setUp() {
        paymentService = new PaymentServiceImpl(APP_KEY);
    }

    @Test
    public void testRetrieveCountPoints() throws JustsendApiClientException {
        Long retrieveCountPoints = paymentService.retrieveCountPoints();
        LOGGER.info("retrieveCountPoints = " + retrieveCountPoints);
    }

    @Test
    public void testCheckBalancePointsUser() throws JustsendApiClientException {
        UserPurseResponse userPurseResponse = paymentService.checkBalancePointsUser();
        LOGGER.info("retrieveCountPoints = " + new ReflectionToStringBuilder(userPurseResponse, ToStringStyle.SHORT_PREFIX_STYLE));
    }

    @Test
    public void testSetBalancePointsUser() throws JustsendApiClientException {
        String setBalancePointsUserResponse = paymentService.setBalancePointsUser(2801, 2L);
        LOGGER.info("setBalancePointsUserResponse = " + setBalancePointsUserResponse);
    }
}