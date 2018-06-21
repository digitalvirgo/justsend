package pl.justsend.api.client.services;

import pl.justsend.api.client.model.Account;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.model.SubAccountRaw;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

/**

 * User: posiadacz
 * Date: 21.03.18
 * Time: 15:43
 */
public interface AccountService {

    Account retrieveAccount() throws JustsendApiClientException;

    SubAccount createSubAccount(SubAccountRaw subAccountRaw) throws JustsendApiClientException;

    String deactivateAccount(String userAppKey) throws JustsendApiClientException;

    SubAccount editSubAccount(String userAppKey, String firstName, String surname, String password, String description) throws JustsendApiClientException;

    String resetSubAccount(String userAppKey) throws JustsendApiClientException;

    SubAccount retrieveSubAccount(String userAppKey) throws JustsendApiClientException;

    List<SubAccount> retrieveSubAccountsList() throws JustsendApiClientException;

}
