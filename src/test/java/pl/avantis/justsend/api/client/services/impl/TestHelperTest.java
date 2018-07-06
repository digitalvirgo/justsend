package pl.avantis.justsend.api.client.services.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestHelperTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckIfProdUrl() {
        //given
        BaseService baseService = new BaseService("") {
            @Override
            public String getJUSTSEND_API_URL() {
                return "justsend.pl";
            }
        };

        //when
        TestHelper.checkIfProdUrl(baseService);
    }
}