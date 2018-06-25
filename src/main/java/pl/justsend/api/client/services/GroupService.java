package pl.justsend.api.client.services;

import pl.justsend.api.client.model.Group;
import pl.justsend.api.client.model.GroupCreate;
import pl.justsend.api.client.model.GroupResponse;
import pl.justsend.api.client.model.GroupUpdate;
import pl.justsend.api.client.model.dto.GroupDTO;
import pl.justsend.api.client.model.dto.PrefixDTO;
import pl.justsend.api.client.model.dto.PrefixReservationDTO;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.File;
import java.util.List;

public interface GroupService {

    /**
     * Zwraca listę grup
     *
     * @return lista group
     */

    List<GroupResponse> retrieveGroups() throws JustsendApiClientException;

    /**
     * Zwraca grupę wraz z listą numerów telefonów wchodzących w jej skład
     *
     * @param groupId Id grupy
     * @return
     */

    Group retrieveGroup(Long groupId) throws JustsendApiClientException;

    /**
     * Tworzy grupę
     *
     * @param groupCreate create group request
     * @return group Id (Text: Created group id: {number})
     */

    String createGroup(GroupCreate groupCreate) throws JustsendApiClientException;

    /**
     * Usuwa grupę
     *
     * @param groupId numer grupy
     * @return text: Removed group id: {groupId}
     */

    String removeGroup(Long groupId) throws JustsendApiClientException;

    /**
     * Dodaje nowe numery telefonów do istniejącej grupy
     *
     * @param groupUpdate
     * @return text: Added: 1 numbers
     */

    String addNumberToGroup(GroupUpdate groupUpdate) throws JustsendApiClientException;

    /**
     * Usuwa z grupy wskazane numery telefonów
     *
     * @param groupUpdate groupUpdate request
     * @return text: Removed: 2 numbers
     */

    String removeNumbersFromGroup(GroupUpdate groupUpdate) throws JustsendApiClientException;

    /**
     * Zwraca listę wolnych prefixów
     *
     * @return
     */

    List<PrefixReservationDTO> getGroupPrefix() throws JustsendApiClientException;

    /**
     * Rezerwuje prefix
     *
     * @param prefixReservationDTO
     * @return info o zarezerowowanym prefiksie
     */

    PrefixDTO reservationPrefix(PrefixReservationDTO prefixReservationDTO) throws JustsendApiClientException;

    /**
     * Dodaje numery z pliku do grupy
     *
     * @param groupId Identyfikator grupy
     * @param inputData
     * @return text: Successful
     * @throws JustsendApiClientException
     */

    String addMsisdnToGroup(Long groupId, File inputData) throws JustsendApiClientException;

    /**
     * Aktualizuje grupę
     *
     * @param groupDTO
     * @return
     */

    GroupDTO updateGroup(GroupDTO groupDTO) throws JustsendApiClientException;
}
