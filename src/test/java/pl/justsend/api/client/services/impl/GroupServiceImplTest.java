package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.model.dto.GroupDTO;
import pl.justsend.api.client.model.dto.GroupMemberDTO;
import pl.justsend.api.client.model.dto.PrefixDTO;
import pl.justsend.api.client.model.dto.PrefixReservationDTO;
import pl.justsend.api.client.model.enums.PrefixType;
import pl.justsend.api.client.services.BaseService;
import pl.justsend.api.client.services.GroupService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static java.lang.Long.valueOf;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.model.enums.PrefixAccessType.GLOBAL;
import static pl.justsend.api.client.services.impl.BaseTest.APP_KEY;
import static pl.justsend.api.client.services.impl.BaseTest.APP_KEY_ADMINISTRATOR;

public class GroupServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(GroupServiceImplTest.class);
    private GroupService groupService;
    private PrefixServiceImpl prefixService;

    @BeforeMethod
    public void setUp() {
        groupService = new GroupServiceImpl(APP_KEY);
        prefixService = new PrefixServiceImpl(APP_KEY_ADMINISTRATOR);
    }

    @Test
    public void testGroupFlow() throws JustsendApiClientException {
        LOGGER.info("=========   create group  ===========");
        GroupCreate createGroup = groupCreate();
        String group = groupService.createGroup(createGroup);
        assertThat(group).startsWith("Created group id: ");

        Long groupID = getGroupID(group);
        LOGGER.info(group);

        LOGGER.info("=========   removeNumbersFromGroup  ===========");
        GroupUpdate removeNumbersFromGroupRequest = removeNumbersFromGroup(groupID);
        String removeNumbersFromGroup = groupService.removeNumbersFromGroup(removeNumbersFromGroupRequest);
        assertThat(removeNumbersFromGroup).isEqualTo("Removed: 2 numbers");

        LOGGER.info("=========   addNumberToGroup  ===========");
        GroupUpdate addNumberToGroupRequest = addNumberToGroupRequest(groupID);
        String addNumberToGroup = groupService.addNumberToGroup(addNumberToGroupRequest);
        assertThat(addNumberToGroup).isEqualTo("Added: 1 numbers");

        LOGGER.info("=========   retrieve group  ===========");
        Group retrieveGroup = groupService.retrieveGroup(groupID);
        assertThat(retrieveGroup.getGroupId()).isEqualTo(groupID);
        assertThat(retrieveGroup.getMembers()).containsExactly("514746372");

        LOGGER.info("=========   updateGroup  ===========");
        String number = "514673908";
        GroupDTO updateGroupRequest = updateGroup(groupID, number);
        GroupDTO updateGroupResponse = groupService.updateGroup(updateGroupRequest);
        Assertions.assertThat(updateGroupResponse.getMembers().get(1).getMsisdn()).isEqualTo(number);

        LOGGER.info("=========   addMsisdnToGroup  ===========");
        String groupResponses = groupService.addMsisdnToGroup(groupID, new TestHelper().getFile("groupMisin.txt"));
        Assertions.assertThat(groupResponses).isEqualTo("Successful");

        LOGGER.info("=========   remove group  ===========");
        String removeGroup = groupService.removeGroup(groupID);
        assertThat(removeGroup).startsWith("Removed group id:");
    }

    @Test
    public void retrieveGroupTest() throws JustsendApiClientException {
        List<GroupResponse> groupResponses = groupService.retrieveGroups();
        Assertions.assertThat(groupResponses).isNotNull();
        Assertions.assertThat(groupResponses).isNotEmpty();
    }

    @Test
    public void reservationPrefixTest() throws JustsendApiClientException {
        //given
        String prefixName = "prefixTest";
        PrefixDTO prefixCreationResponse = prefixService.createPrefix(createPrefixRequest(prefixName));
        LOGGER.info(" prefixResponse = " + TestHelper.toString(prefixCreationResponse));

        PrefixReservationDTO prefixReservation = new PrefixReservationDTO();
        prefixReservation.setId(prefixCreationResponse.getId());
        prefixReservation.setName(prefixName);
        prefixReservation.setReservation(true);
        prefixReservation.setDateTo(new Date());

        //when
        PrefixDTO prefixReservationResponse = groupService.reservationPrefix(prefixReservation);

        //then
        assertThat(prefixReservationResponse.getName()).isEqualTo(prefixCreationResponse.getName());
        assertThat(prefixReservationResponse.getId()).isEqualTo(prefixCreationResponse.getId());
    }

    private PrefixDTO createPrefixRequest(String prefixName) {
        PrefixDTO prefixDTO = new PrefixDTO();
        prefixDTO.setPrefixType(PrefixType.GROUP_COMMAND);
        prefixDTO.setName(prefixName);
        prefixDTO.setPrefixAccessType(GLOBAL);
        return prefixDTO;
    }

    @Test
    public void groupPrefixTest() throws JustsendApiClientException {
        List<PrefixReservationDTO> groupResponses = groupService.getGroupPrefix();
        Assertions.assertThat(groupResponses).isNotNull();
        Assertions.assertThat(groupResponses).isNotEmpty();
    }

    private GroupDTO updateGroup(Long groupID, String number) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(groupID);
        groupDTO.getMembers().add(new GroupMemberDTO(number));
        return groupDTO;
    }

    private GroupUpdate addNumberToGroupRequest(long groupId) {
        GroupUpdate groupUpdate = new GroupUpdate();
        groupUpdate.setGroupId(groupId);
        groupUpdate.setMembers(asList("514746372"));
        return groupUpdate;
    }

    private GroupUpdate removeNumbersFromGroup(long groupId) {
        GroupUpdate groupUpdate = new GroupUpdate();
        groupUpdate.setGroupId(groupId);
        groupUpdate.setMembers(asList("Number1", "Number2"));
        return groupUpdate;
    }

    public static GroupCreate groupCreate() {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName("name");
        groupCreate.setMembers(asList("Number1", "Number2"));
        return groupCreate;
    }


    public static Long getGroupID(String group) {
        String groupId = group.split(":")[1].trim().replace(",", "");
        return valueOf(groupId);
    }

    private class PrefixServiceImpl extends BaseService {

        private PrefixServiceImpl(String appKey) {
            super(appKey);
        }

        private PrefixDTO createPrefix(final PrefixDTO prefixDTO) throws JustsendApiClientException {
            String url = createURL("/prefix/createPrefix/{appKey}");

            try {
                JSResponse jsResponse = justsendHttpClient.doPost(url, prefixDTO);
                return processResponse(jsResponse, PrefixDTO.class);

            } catch (IOException e) {
                throw new JustsendApiClientException("connection failed: " + e.getMessage());
            }
        }
    }
}