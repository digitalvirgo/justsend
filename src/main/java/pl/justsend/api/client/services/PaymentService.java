package pl.justsend.api.client.services;

import pl.justsend.api.client.model.UserPurseResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

public interface PaymentService {

    /**
     * Zwraca liczbę punktów, która jest na koncie użytkownika
     *
     * @return liczba punktów
     */

    Long retrieveCountPoints() throws JustsendApiClientException;

    /**
     * Sprawdzenie liczby punktów na koncie podużytkownika
     *
     * @return balanceInPoints and freePoint w odpowiedzi
     */

    UserPurseResponse checkBalancePointsUser() throws JustsendApiClientException;

    /**
     * Dodawanie/usuwanie punktów dla podużytkownika
     *
     * @param subId SubUser id
     * @param points Ilość punktów (+/-)
     * @return text: Balance points for SubAccount with id {subAccountID} and his Master with id {accountID} was changed
     */

    String setBalancePointsUser(Integer subId, Long points) throws JustsendApiClientException;
}
