package pl.avantis.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import static pl.avantis.justsend.api.client.services.impl.enums.AddressPostBackType.SHIPMENTS_STATUS;

public class PostBackServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(PaymentServiceImplTest.class);

    private PostBackServiceImpl postBackService;

    @BeforeClass
    public void setUp() {
        postBackService = new PostBackServiceImpl(TestHelper.APP_KEY);
    }

    @Test
    public void testSendPostBack() throws JustsendApiClientException {
        String response = postBackService.sendPostBack(SHIPMENTS_STATUS, "StringJson");
        Assertions.assertThat(response).isEqualTo("OK");
    }

}