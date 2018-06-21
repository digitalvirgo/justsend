package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.enums.AddressPostBackType;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import static pl.justsend.api.client.model.enums.AddressPostBackType.DELIVERY_STATUS;
import static pl.justsend.api.client.model.enums.AddressPostBackType.SHIPMENTS_STATUS;
import static pl.justsend.api.client.test.services.BaseTest.APP_KEY;

public class PostBackServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(PaymentServiceImplTest.class);

    private PostBackServiceImpl postBackService;

    @BeforeClass
    public void setUp() {
        postBackService = new PostBackServiceImpl(APP_KEY);
    }

    @Test
    public void testSendPostBack() throws JustsendApiClientException {
        String response = postBackService.sendPostBack(SHIPMENTS_STATUS, "StringJson");
        Assertions.assertThat(response).isNotNull();
        LOGGER.info("response = " + response);
    }

}