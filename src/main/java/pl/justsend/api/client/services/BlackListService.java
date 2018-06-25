package pl.justsend.api.client.services;

import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

/**

 * User: posiadacz
 * Date: 21.03.18
 * Time: 15:46
 */
public interface BlackListService {

    String addNumbersToBlackList(List<String> msisdnList) throws JustsendApiClientException;

    String removeNumbersFromBlackList(List<String> msisdnList) throws JustsendApiClientException;

    List<String> retrieveNumbers() throws JustsendApiClientException;

}
