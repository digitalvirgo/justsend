package pl.digitalvirgo.justsend.api.client.services.impl;

import org.testng.annotations.DataProvider;
import pl.digitalvirgo.justsend.api.client.test.helpers.BaseServiceHelper;

import java.util.Arrays;
import java.util.List;

public class BlackListServiceTestDataProvider extends BaseServiceHelper {

    protected static final String ADD_MSISDN_RESPONSE_REGEX = "Added: [0-9]* numbers";
    protected static final String REMOVE_MSISDN_RESPONSE_REGEX = "Removed: [0-9]* numbers";


    @DataProvider
    protected static Object[][] blackListFlowTestDataProvider() {
        return new Object[][]{
                {getInputAsArray("48505948385", "48505948386", "48505948387"), getInputAsArray("48502948385", "48502948386")}
        };
    }

    @DataProvider
    protected static Object[][] blackListIncorrectMsisdnDataProvider() {
        return new Object[][]{
                {getInputAsArray("485059", "23")}
        };
    }

    private static List<String> getInputAsArray(String... s) {
        return Arrays.asList(s);
    }

}
