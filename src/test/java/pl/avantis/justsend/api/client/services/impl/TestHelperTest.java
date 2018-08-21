package pl.avantis.justsend.api.client.services.impl;

import org.testng.annotations.Test;

public class TestHelperTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckIfProdUrl() {
        //given
        Constants.JUSTSEND_API_URL = "justsend.pl";

        //when
        TestHelper.checkIfProdUrl();
    }
}