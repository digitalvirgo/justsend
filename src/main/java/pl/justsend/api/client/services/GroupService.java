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
    List<GroupResponse> retrieveGroups() throws JustsendApiClientException;

    Group retrieveGroup(Long groupId) throws JustsendApiClientException;

    String createGroup(GroupCreate groupCreate) throws JustsendApiClientException;

    String removeGroup(Long groupId) throws JustsendApiClientException;

    String addNumberToGroup(
            GroupUpdate groupUpdate) throws JustsendApiClientException;

    String removeNumbersFromGroup(
            GroupUpdate groupUpdate) throws JustsendApiClientException;

    List<PrefixReservationDTO> getGroupPrefix() throws JustsendApiClientException;

    PrefixDTO reservationPrefix(PrefixReservationDTO prefixReservationDTO) throws JustsendApiClientException;

    String addMsisdnToGroup(
            Long groupId,
            File inputData) throws JustsendApiClientException;

    GroupDTO updateGroup(GroupDTO groupDTO) throws JustsendApiClientException;
}
