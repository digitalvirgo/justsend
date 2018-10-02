package pl.digitalvirgo.justsend.api.client.test.helpers;

import pl.digitalvirgo.justsend.api.client.services.impl.Constants;

public class TestConstants {

    public static final String MOCK_API_URL = "https://justsend-mock-service.staging.digitalvirgo.pl/justsend-mock";
    public static final String SANDBOX_API_URL = "https://justsend-api-sandbox.staging.digitalvirgo.pl/api/rest";
    public static final String STAGING_API_URL = "http://justsend-api.dcos.staging.avantis.pl/api/rest";
    public static final String STAGING2_API_URL = "https://justsend-api-staging2.staging.digitalvirgo.pl/api/rest";

    static {
        Constants.JUSTSEND_API_URL = TestConstants.STAGING_API_URL;
    }

}
