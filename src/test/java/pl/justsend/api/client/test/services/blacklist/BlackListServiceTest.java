package pl.justsend.api.client.test.services.blacklist;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.services.exception.JustsendApiClientException;
import pl.justsend.api.client.services.impl.BlackListServiceImpl;

import java.util.List;

/**

 * User: posiadacz
 * Date: 30.03.18
 * Time: 13:17
 */
public class BlackListServiceTest extends BlackListServiceTestDataProvider {

    @BeforeClass
    public void init() {
        blackListService = new BlackListServiceImpl(APP_KEY);
    }

    @Test
    public void retrieveNumbersTest() throws JustsendApiClientException {
        List<String> listNumbers = blackListService.retrieveNumbers();
        Assert.assertNotNull(listNumbers);
    }

    @Test(dataProvider = "blackListFlowTestDataProvider")
    public void addNumbersToBlackListTest(List<String> firstMsisdnList, List<String> secondMsisdnList) throws JustsendApiClientException {
        List<String> listNumbers = blackListService.retrieveNumbers();
        blackListService.addNumbersToBlackList(firstMsisdnList);
        Assert.assertTrue(listNumbers.size() >= firstMsisdnList.size());
    }

    @Test(dataProvider = "blackListIncorrectMsisdnDataProvider")
    public void addNumbersToBlackListIncorrectMisdnTest(List<String> msisdnList) throws JustsendApiClientException {
        List<String> beforeListNumbers = blackListService.retrieveNumbers();
        blackListService.addNumbersToBlackList(msisdnList);
        List<String> afterListNumbers = blackListService.retrieveNumbers();
        Assert.assertEquals(beforeListNumbers.size(), afterListNumbers.size());
    }

    @Test(dataProvider = "blackListFlowTestDataProvider")
    public void removeNumbersFromBlackListTest(List<String> firstMsisdnList, List<String> secondMsisdnList) throws JustsendApiClientException {
        List<String> listNumbers = blackListService.retrieveNumbers();
        blackListService.removeNumbersFromBlackList(firstMsisdnList);
        Assert.assertTrue(listNumbers.size() <= firstMsisdnList.size());
    }

    @Test(dataProvider = "blackListFlowTestDataProvider")
    public void blackListFlowTest(List<String> firstMsisdnList, List<String> secondMsisdnList) throws JustsendApiClientException {

        /* init parameters */
        String response;
        List<String> savedMsisdns;

        /* clear saved msisdn list */
        savedMsisdns = blackListService.retrieveNumbers();
        Assert.assertNotNull(savedMsisdns);
        if (savedMsisdns.size() > 0) {
            blackListService.removeNumbersFromBlackList(savedMsisdns);
        }

        /* add first list*/
        response = blackListService.addNumbersToBlackList(firstMsisdnList);
        Assert.assertTrue(response.matches(ADD_MSISDN_RESPONSE_REGEX));

        /* validate saved list */
        savedMsisdns = blackListService.retrieveNumbers();
        Assert.assertEquals(savedMsisdns.size(), firstMsisdnList.size());

        /* remove first list */
        response = blackListService.removeNumbersFromBlackList(firstMsisdnList);
        Assert.assertTrue(response.matches(REMOVE_MSISDN_RESPONSE_REGEX));

        /* add first list */
        response = blackListService.addNumbersToBlackList(firstMsisdnList);
        Assert.assertTrue(response.matches(ADD_MSISDN_RESPONSE_REGEX));

        /* add second list*/
        response = blackListService.addNumbersToBlackList(secondMsisdnList);
        Assert.assertTrue(response.matches(ADD_MSISDN_RESPONSE_REGEX));

        /* validate saved list */
        savedMsisdns = blackListService.retrieveNumbers();
        Assert.assertEquals(savedMsisdns.size(), firstMsisdnList.size() + secondMsisdnList.size());

        /* remove saved list */
        response = blackListService.removeNumbersFromBlackList(savedMsisdns);
        Assert.assertTrue(response.matches(REMOVE_MSISDN_RESPONSE_REGEX));

        /* validate saved list */
        savedMsisdns = blackListService.retrieveNumbers();
        Assert.assertEquals(savedMsisdns.size(), 0);

    }

}
