package pl.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.model.dto.GroupDTO;
import pl.justsend.api.client.model.dto.PrefixDTO;
import pl.justsend.api.client.model.dto.PrefixReservationDTO;
import pl.justsend.api.client.services.BaseService;
import pl.justsend.api.client.services.GroupService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

public class GroupServiceImpl extends BaseService implements GroupService {

    public GroupServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public List<GroupResponse> retrieveGroups() throws JustsendApiClientException {
        String url = createURL("/group/list/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<GroupResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Group retrieveGroup(final Long groupId) throws JustsendApiClientException {
        String url = createURL("/group/{appKey}/{groupId}", "groupId", valueOf(groupId));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Group.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String createGroup(GroupCreate groupCreate) throws JustsendApiClientException {
        String url = createURL("/group/add/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, groupCreate);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String removeGroup(final Long groupId) throws JustsendApiClientException {
        String url = createURL("/group/remove/{appKey}/{groupId}","groupId", valueOf(groupId));

        try {

            JSResponse jsResponse = justsendHttpClient.doDelete(url);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String addNumberToGroup(
            final GroupUpdate groupUpdate) throws JustsendApiClientException {

        String url = createURL("/group/add/numbers/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doPut(url, groupUpdate);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String removeNumbersFromGroup(
            final GroupUpdate groupUpdate) throws JustsendApiClientException {
        String url = createURL("/group/remove/numbers/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doPut(url, groupUpdate);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<PrefixReservationDTO> getGroupPrefix() throws JustsendApiClientException {
        String url = createURL("/group/getGroupPrefix/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<PrefixReservationDTO>>(){}.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public PrefixDTO reservationPrefix(final PrefixReservationDTO prefixReservationDTO) throws JustsendApiClientException {
        String url = createURL("/group/reservationPrefix/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url,prefixReservationDTO);
            return processResponse(jsResponse, PrefixDTO.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String addMsisdnToGroup(
            final Long groupId,
            final File inputData) throws JustsendApiClientException {
        String url = createURL("/group/addMsisdns/{appKey}/{groupId}","groupId", valueOf(groupId));

        try {
            //TODO check the  MultipartFile serialization to json
            JSResponse jsResponse = justsendHttpClient.doMultiPartPost(url, inputData);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public GroupDTO updateGroup(final GroupDTO groupDTO) throws JustsendApiClientException {
        String url = createURL("/group/updateGroup/{appKey}");

        try {
            JSResponse jsResponse = justsendHttpClient.doPost(url, groupDTO);
            return processResponse(jsResponse, GroupDTO.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


}
