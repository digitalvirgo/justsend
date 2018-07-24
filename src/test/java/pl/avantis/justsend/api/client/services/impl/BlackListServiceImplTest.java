package pl.avantis.justsend.api.client.services.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.checkIfProdUrl;
import static pl.avantis.justsend.api.client.test.helpers.DataGenerator.getIncorrectPhoneNumber;
import static pl.avantis.justsend.api.client.test.helpers.DataGenerator.getRandomPhoneNumber;

public class BlackListServiceImplTest extends BlackListServiceTestDataProvider {

    private BlackListServiceImpl blackListService;

    @BeforeClass
    public void init() {
        blackListService = new BlackListServiceImpl(APP_KEY);
        checkIfProdUrl(blackListService);
    }

    @Test
    public void retrieveNumbersTest() throws JustsendApiClientException {
        List<String> listNumbers = blackListService.retrieveNumbers();
        Assert.assertNotNull(listNumbers);
    }

    @Test
    public void addNumbersToBlackListTest() throws JustsendApiClientException {
        //given
        List<String> numbers = asList(getRandomPhoneNumber(), getRandomPhoneNumber());

        //when
        String response = blackListService.addNumbersToBlackList(numbers);

        //then
        assertThat(response).isEqualTo("Added: 2 numbers");
        List<String> afterListNumbers = blackListService.retrieveNumbers();
        assertThat(afterListNumbers).contains(numbers.get(0), numbers.get(1));
    }

    @Test
    public void addNumbersToBlackListIncorrectMisdnTest() throws JustsendApiClientException {
        //given
        List<String> beforeListNumbers = blackListService.retrieveNumbers();

        //when
        List<String> incorrectPhoneNumbers = asList(getIncorrectPhoneNumber(), getIncorrectPhoneNumber());
        blackListService.addNumbersToBlackList(incorrectPhoneNumbers);

        //then
        List<String> afterListNumbers = blackListService.retrieveNumbers();
        Assert.assertEquals(beforeListNumbers.size(), afterListNumbers.size());
    }

    @Test
    public void removeNumbersFromBlackListTest() throws JustsendApiClientException {
        //given
        String randomPhoneNumber = getRandomPhoneNumber();
        blackListService.addNumbersToBlackList(asList(randomPhoneNumber));

        //when
        String response = blackListService.removeNumbersFromBlackList(asList(randomPhoneNumber));

        //then
        assertThat(response).isEqualTo("Removed: 1 numbers");
        List<String> afterBlackListNumbers = blackListService.retrieveNumbers();
        assertThat(afterBlackListNumbers).doesNotContain(randomPhoneNumber);
    }

    @Test(dataProvider = "blackListFlowTestDataProvider")
    public void blackListFlowTest(List<String> firstMsisdnList, List<String> secondMsisdnList) throws JustsendApiClientException {

        /* init parameters */
        String response;
        List<String> savedMsisdns;

        /* clear saved number list */
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
