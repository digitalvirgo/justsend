package pl.avantis.justsend.api.client.services.impl.services;

import pl.avantis.justsend.api.client.model.Group;
import pl.avantis.justsend.api.client.model.GroupDTO;
import pl.avantis.justsend.api.client.model.GroupResponse;
import pl.avantis.justsend.api.client.model.GroupUpdate;
import pl.avantis.justsend.api.client.model.Prefix;
import pl.avantis.justsend.api.client.model.PrefixReservation;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.File;
import java.util.List;

public interface GroupService {

    /**
     * Zwraca listę grup
     *
     * @return lista group
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<GroupResponse> retrieveGroups() throws JustsendApiClientException;

    /**
     * Zwraca grupę wraz z listą numerów telefonów wchodzących w jej skład
     *
     * @param groupId Id grupy
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Group retrieveGroup(Long groupId) throws JustsendApiClientException;

    /**
     * Tworzy grupę
     *
     * @param group Dane do grupy
     * @param file Plik z numerami
     * @return group Id (Text: Created group id: {number})
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String createGroup(String group, File file) throws JustsendApiClientException;

    /**
     * Usuwa grupę
     *
     * @param groupId numer grupy
     * @return text: Removed group id: {groupId}
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String removeGroup(Long groupId) throws JustsendApiClientException;

    /**
     * Dodaje nowe numery telefonów do istniejącej grupy
     *
     * @param groupUpdate
     * @return text: Added: 1 numbers
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String addNumberToGroup(GroupUpdate groupUpdate) throws JustsendApiClientException;

    /**
     * Usuwa z grupy wskazane numery telefonów
     *
     * @param groupUpdate groupUpdate request
     * @return text: Removed: 2 numbers
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String removeNumbersFromGroup(GroupUpdate groupUpdate) throws JustsendApiClientException;

    /**
     * Zwraca listę wolnych prefixów
     *
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<PrefixReservation> getGroupPrefix() throws JustsendApiClientException;

    /**
     * Rezerwuje prefix
     *
     * @param prefixReservation
     * @return info o zarezerowowanym prefiksie
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Prefix reservationPrefix(PrefixReservation prefixReservation) throws JustsendApiClientException;

    /**
     * Dodaje numery z pliku do grupy
     *
     * @param groupId Identyfikator grupy
     * @param inputData
     * @return text: Successful
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String addNumbersToGroup(Long groupId, File inputData) throws JustsendApiClientException;

    /**
     * Aktualizuje grupę
     *
     * @param groupDTO
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    GroupDTO updateGroup(GroupDTO groupDTO) throws JustsendApiClientException;
}
