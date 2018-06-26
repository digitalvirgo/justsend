package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.model.enums.PrefixType;
import pl.justsend.api.client.services.GroupService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.Date;
import java.util.List;

import static java.lang.Long.valueOf;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.model.enums.PrefixAccessType.GLOBAL;
import static pl.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.justsend.api.client.services.impl.TestHelper.APP_KEY_ADMINISTRATOR;

public class GroupServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(GroupServiceImplTest.class);
    private GroupService groupService;
    private PrefixServiceImpl prefixService;
    private GroupCreate createGroup;
    private Long groupID;

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

    @BeforeMethod
    public void setUp() throws JustsendApiClientException {

        groupService = new GroupServiceImpl(APP_KEY);
        prefixService = new PrefixServiceImpl(APP_KEY_ADMINISTRATOR);

        createGroup = groupCreate();
        String group = groupService.createGroup(createGroup);
        assertThat(group).startsWith("Created group id: ");

        groupID = getGroupID(group);
        LOGGER.info(group);
    }

    @Test
    public void testRetrieveGroup() throws JustsendApiClientException {
        //when
        Group retrieveGroup = groupService.retrieveGroup(groupID);
        //then
        assertThat(retrieveGroup.getGroupId()).isEqualTo(groupID);
    }

    @Test
    public void testCreateGroup() throws JustsendApiClientException {
        createGroup = groupCreate();
        String group = groupService.createGroup(createGroup);
        assertThat(group).startsWith("Created group id: ");
    }

    @Test
    public void testRemoveGroup() throws JustsendApiClientException {
        //when
        String removeGroup = groupService.removeGroup(groupID);

        //then
        assertThat(removeGroup).startsWith("Removed group id:");
    }

    @Test
    public void testAddNumberToGroup() throws JustsendApiClientException {
        //given
        GroupUpdate addNumberToGroupRequest = addNumberToGroupRequest(groupID);

        //when
        String addNumberToGroup = groupService.addNumberToGroup(addNumberToGroupRequest);

        //then
        assertThat(addNumberToGroup).isEqualTo("Added: 1 numbers");
    }

    @Test
    public void testRemoveNumbersFromGroup1() throws JustsendApiClientException {
        //given
        GroupUpdate removeNumbersFromGroupRequest = removeNumbersFromGroup(groupID);

        //when
        String removeNumbersFromGroup = groupService.removeNumbersFromGroup(removeNumbersFromGroupRequest);

        //then
        assertThat(removeNumbersFromGroup).isEqualTo("Removed: 2 numbers");
    }

    @Test
    public void testAddMsisdnToGroup() throws JustsendApiClientException {
        //TODO: why it adds two times same number
        String groupResponses = groupService.addNumbersToGroup(groupID, new TestHelper().getFile("groupMisin.txt"));
        Assertions.assertThat(groupResponses).isEqualTo("Successful");
    }

    @Test
    public void testUpdateGroup1() throws JustsendApiClientException {
        //given
        String number = "514673908";

        //when
        GroupDTO updateGroupRequest = updateGroup(groupID, number);
        GroupDTO updateGroupResponse = groupService.updateGroup(updateGroupRequest);

        //then
        Assertions.assertThat(updateGroupResponse.getMembers().get(2).getNumber()).isEqualTo(number);
    }

    @Test
    public void retrieveGroupTest() throws JustsendApiClientException {
        //when
        List<GroupResponse> groupResponses = groupService.retrieveGroups();

        //then
        Assertions.assertThat(groupResponses).isNotNull();
        Assertions.assertThat(groupResponses).isNotEmpty();
    }

    @Test
    public void reservationPrefixTest() throws JustsendApiClientException {
        //given
        String prefixName = "prefixTest";
        Prefix prefixCreationResponse = prefixService.createPrefix(createPrefixRequest(prefixName));
        LOGGER.info(" prefixResponse = " + TestHelper.toString(prefixCreationResponse));

        PrefixReservation prefixReservation = new PrefixReservation();
        prefixReservation.setId(prefixCreationResponse.getId());
        prefixReservation.setName(prefixName);
        prefixReservation.setReservation(true);
        prefixReservation.setDateTo(new Date());

        //when
        Prefix prefixReservationResponse = groupService.reservationPrefix(prefixReservation);

        //then
        assertThat(prefixReservationResponse.getName()).isEqualTo(prefixCreationResponse.getName());
        assertThat(prefixReservationResponse.getId()).isEqualTo(prefixCreationResponse.getId());
    }

    @Test
    public void groupPrefixTest() throws JustsendApiClientException {
        List<PrefixReservation> groupResponses = groupService.getGroupPrefix();
        Assertions.assertThat(groupResponses).isNotNull();
        Assertions.assertThat(groupResponses).isNotEmpty();
    }

    private Prefix createPrefixRequest(String prefixName) {
        Prefix prefix = new Prefix();
        prefix.setPrefixType(PrefixType.GROUP_COMMAND);
        prefix.setName(prefixName);
        prefix.setPrefixAccessType(GLOBAL);
        return prefix;
    }

    private GroupDTO updateGroup(Long groupID, String number) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(groupID);
        groupDTO.getMembers().add(new GroupMember(number));
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
}