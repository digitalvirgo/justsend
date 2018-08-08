package pl.avantis.justsend.api.client.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import pl.avantis.justsend.api.client.model.Group;
import pl.avantis.justsend.api.client.model.GroupCreate;
import pl.avantis.justsend.api.client.model.GroupDTO;
import pl.avantis.justsend.api.client.model.GroupResponse;
import pl.avantis.justsend.api.client.model.GroupUpdate;
import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.model.Prefix;
import pl.avantis.justsend.api.client.model.PrefixReservation;
import pl.avantis.justsend.api.client.services.impl.services.GroupService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

public class GroupServiceImpl extends BaseService implements GroupService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * @param appKey Klucz api
     */

    public GroupServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public List<GroupResponse> retrieveGroups() throws JustsendApiClientException {
        String url = createURL("/v2/group/list");

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
        String url = createURL("/v2/group/{groupId}", "groupId", valueOf(groupId));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Group.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String createGroup(GroupCreate groupCreate, File file) throws JustsendApiClientException {
        String url = createURL("/v2/group/add");

        try {
            String groupCreateText = OBJECT_MAPPER.writeValueAsString(groupCreate);
            JSResponse jsResponse = justsendHttpClient.doMultiPartPost(url, "input", file, "group", groupCreateText);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String createGroup(GroupCreate groupCreate) throws JustsendApiClientException {
        String url = createURL("/v2/group/addE");

        try {
            JSResponse jsResponse = justsendHttpClient.doPost(url, groupCreate);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String removeGroup(final Long groupId) throws JustsendApiClientException {
        String url = createURL("/v2/group/remove/{groupId}", "groupId", valueOf(groupId));

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

        String url = createURL("/v2/group/add/numbers");

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
        String url = createURL("/v2/group/remove/numbers");

        try {

            JSResponse jsResponse = justsendHttpClient.doPut(url, groupUpdate);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<PrefixReservation> getGroupPrefix() throws JustsendApiClientException {
        String url = createURL("/v2/group/getGroupPrefix");

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<PrefixReservation>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Prefix reservationPrefix(final PrefixReservation prefixReservation) throws JustsendApiClientException {
        String url = createURL("/v2/group/reservationPrefix");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, prefixReservation);
            return processResponse(jsResponse, Prefix.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String addMsisdnToGroup(final Long groupId, final File inputData) throws JustsendApiClientException {
        String url = createURL("/v2/group/addMsisdns/{groupId}", "groupId", valueOf(groupId));

        try {
            JSResponse jsResponse = justsendHttpClient.doMultiPartPost(url, "inputData", inputData, null, null);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


    @Override
    public GroupDTO updateGroup(final GroupDTO groupDTO) throws JustsendApiClientException {
        String url = createURL("/v2/group/updateGroup");

        try {
            JSResponse jsResponse = justsendHttpClient.doPost(url, groupDTO);
            return processResponse(jsResponse, GroupDTO.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


}
