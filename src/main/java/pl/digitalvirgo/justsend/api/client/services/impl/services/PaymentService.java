package pl.digitalvirgo.justsend.api.client.services.impl.services;

import pl.digitalvirgo.justsend.api.client.model.UserPurseResponse;
import pl.digitalvirgo.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

public interface PaymentService {

    /**
     * Zwraca liczbę punktów, która jest na koncie użytkownika
     *
     * @return liczba punktów
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long retrieveCountPoints() throws JustsendApiClientException;

    /**
     * Sprawdzenie liczby punktów na koncie podużytkownika
     *
     * @return balanceInPoints and freePoint w odpowiedzi
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    UserPurseResponse checkBalancePointsUser() throws JustsendApiClientException;

    /**
     * Dodawanie/usuwanie punktów dla podużytkownika
     *
     * @param subId SubUser id
     * @param points Ilość punktów (+/-)
     * @return text: Balance points for SubAccount with id {subAccountID} and his Master with id {accountID} was changed
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String setBalancePointsUser(Integer subId, Long points) throws JustsendApiClientException;
}
