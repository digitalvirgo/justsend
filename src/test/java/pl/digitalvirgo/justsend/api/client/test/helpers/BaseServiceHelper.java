package pl.digitalvirgo.justsend.api.client.test.helpers;

import pl.digitalvirgo.justsend.api.client.services.impl.Constants;

public class BaseServiceHelper {

    public void init(){
        Constants.JUSTSEND_API_URL = TestConstants.STAGING2_API_URL;
    }

}
