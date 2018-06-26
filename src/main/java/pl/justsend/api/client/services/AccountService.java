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

    /**
     * Tworzenie subkonta
     *
     * @param subAccountRaw zapytanie do tworzenia konta
     * @return informacje o utworzonym koncie
     */

    SubAccount createSubAccount(SubAccountRaw subAccountRaw) throws JustsendApiClientException;

    /**
     * Edycja subkonta
     *
     * @param subUserID SubUser id
     * @param firstName Firstname
     * @param surname Surname
     * @param password Password
     * @param description Description
     * @return informacje o koncie
     * @throws JustsendApiClientException
     */

    SubAccount editSubAccount(Long subUserID, String firstName, String surname, String password, String description) throws JustsendApiClientException;

    /**
     * Deaktywacja konta
     *
     * @return text: Slave with id : {0} was deactivated/Master with id : {0} and his Slaves were deactivated
     */

    String deactivateAccount(String userAppKey) throws JustsendApiClientException;

    /**
     * Pobieranie użytkownika
     *
     * @return zwraca informacje o koncie
     */

    Account retrieveAccount() throws JustsendApiClientException;

    /**
     * Resetowanie klucza subkonta
     *
     * @param subId SubUser id
     * @return text: Slave appKey with id {0} has been reset
     */

    String resetSubAccount(Long subId) throws JustsendApiClientException;

    /**
     * Pobieranie podużytkownika
     *
     * @param userAppKey Klucz api (SLAVE)
     * @return zwraca informacje o koncie
     */

    SubAccount retrieveSubAccount(String userAppKey) throws JustsendApiClientException;

    /**
     * Pobieranie listy podużytkowników
     *
     * @return zwraca informacje o listach użytkowników
     */


    List<SubAccount> retrieveSubAccountsList() throws JustsendApiClientException;

}
