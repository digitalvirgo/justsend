package pl.justsend.api.client.services;

import pl.justsend.api.client.model.Account;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.model.SubAccountRaw;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

public interface AccountService {

    /**
     * Tworzenie subkonta
     *
     * @param subAccountRaw zapytanie do tworzenia konta
     * @return informacje o utworzonym koncie
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
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
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    SubAccount editSubAccount(Long subUserID, String firstName, String surname, String password, String description) throws JustsendApiClientException;

    /**
     * Deaktywacja konta
     *
     * @return text: Slave with id : {0} was deactivated/Master with id : {0} and his Slaves were deactivated
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String deactivateAccount(String userAppKey) throws JustsendApiClientException;

    /**
     * Pobieranie użytkownika
     *
     * @return zwraca informacje o koncie
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Account retrieveAccount() throws JustsendApiClientException;

    /**
     * Resetowanie klucza subkonta
     *
     * @param subId SubUser id
     * @return text: Slave appKey with id {0} has been reset
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String resetSubAccount(Long subId) throws JustsendApiClientException;

    /**
     * Pobieranie podużytkownika
     *
     * @param userAppKey Klucz api (SLAVE)
     * @return zwraca informacje o koncie
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    SubAccount retrieveSubAccount(String userAppKey) throws JustsendApiClientException;

    /**
     * Pobieranie listy podużytkowników
     *
     * @return zwraca informacje o listach użytkowników
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */


    List<SubAccount> retrieveSubAccountsList() throws JustsendApiClientException;

}
