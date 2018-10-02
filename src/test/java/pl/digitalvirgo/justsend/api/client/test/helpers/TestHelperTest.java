package pl.digitalvirgo.justsend.api.client.test.helpers;

import org.testng.annotations.Test;
import pl.digitalvirgo.justsend.api.client.services.impl.Constants;

public class TestHelperTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckIfProdUrl() {
        //given
        Constants.JUSTSEND_API_URL = "justsend.pl";

        //when
        TestHelper.checkIfProdUrl();
    }
}