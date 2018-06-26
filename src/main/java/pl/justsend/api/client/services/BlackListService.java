package pl.justsend.api.client.services;

import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

public interface BlackListService {

    /**
     * Dodaje nowe numery telefonów do czarnej listy
     *
     * @param numbers lista numerów
     * @return  text: "Added: {numberAdded} numbers"
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String addNumbersToBlackList(List<String> numbers) throws JustsendApiClientException;

    /**
     * Usuwa numery telefonów z czarnej listy
     *
     * @param numbers lista numerów
     * @return text: Removed: {removedNumbers} numbers
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String removeNumbersFromBlackList(List<String> numbers) throws JustsendApiClientException;

    /**
     * Zwraca listę numerów telefonów wchodzących w skład black listy
     *
     * @return lista numerów
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<String> retrieveNumbers() throws JustsendApiClientException;

}
