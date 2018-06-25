package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.Group;
import pl.justsend.api.client.model.GroupCreate;
import pl.justsend.api.client.model.GroupResponse;
import pl.justsend.api.client.model.GroupUpdate;
import pl.justsend.api.client.model.dto.GroupDTO;
import pl.justsend.api.client.model.dto.PrefixDTO;
import pl.justsend.api.client.model.dto.PrefixReservationDTO;
import pl.justsend.api.client.services.GroupService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.Date;
import java.util.List;

import static java.lang.Long.valueOf;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.test.services.BaseTest.APP_KEY;

public class GroupServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(GroupServiceImplTest.class);

    private GroupService groupService;

    @BeforeMethod
    public void setUp() {
        groupService = new GroupServiceImpl(APP_KEY);
    }

    @Test
    public void testGroupFlow() throws JustsendApiClientException {
        LOGGER.info("=========   create group  ===========");
        GroupCreate createGroup = groupCreate();
        String group = groupService.createGroup(createGroup);

        Long groupID = getGroupID(group);
        LOGGER.info(group);

        LOGGER.info("=========   removeNumbersFromGroup  ===========");
        GroupUpdate removeNumbersFromGroupRequest = removeNumbersFromGroup(groupID);
        String removeNumbersFromGroup = groupService.removeNumbersFromGroup(removeNumbersFromGroupRequest);
        LOGGER.info(removeNumbersFromGroup);

        LOGGER.info("=========   addNumberToGroup  ===========");
        GroupUpdate addNumberToGroupRequest = addNumberToGroupRequest(groupID);
        String addNumberToGroup = groupService.addNumberToGroup(addNumberToGroupRequest);
        LOGGER.info(addNumberToGroup);

        LOGGER.info("=========   retrieve group  ===========");
        Group retrieveGroup = groupService.retrieveGroup(groupID);
        assertThat(retrieveGroup.getGroupId()).isEqualTo(groupID);
        assertThat(retrieveGroup.getMembers()).containsExactly("514746372");

        LOGGER.info("=========   updateGroup  ===========");
        GroupDTO updateGroupRequest = updateGroup(groupID);
        GroupDTO updateGroup = groupService.updateGroup(updateGroupRequest);
        Assertions.assertThat(updateGroup).isNotNull();

        LOGGER.info("=========   addMsisdnToGroup  ===========");
        String groupResponses = groupService.addMsisdnToGroup(groupID, new TestHelper().getFile("addressFile.txt"));
        Assertions.assertThat(groupResponses).isNotNull();

        LOGGER.info("=========   remove group  ===========");
        String removeGroup = groupService.removeGroup(groupID);
        LOGGER.info(removeGroup);
    }

    @Test
    public void retrieveGroupTest() throws JustsendApiClientException {
        List<GroupResponse> groupResponses = groupService.retrieveGroups();
        Assertions.assertThat(groupResponses).isNotNull();
        Assertions.assertThat(groupResponses).isNotEmpty();
    }

    @Test(expectedExceptions = {JustsendApiClientException.class}, expectedExceptionsMessageRegExp="null - Prefix limit has been exceeded")
    public void reservationPrefixTest() throws JustsendApiClientException {
        //given
        PrefixReservationDTO prefixReservation = new PrefixReservationDTO();
        prefixReservation.setName("Na");
        prefixReservation.setReservation(true);
        prefixReservation.setDateTo(new Date());

        //when
        PrefixDTO prefixDTO = groupService.reservationPrefix(prefixReservation);

        //TODO message='Prefix limit has been exceeded'
        //then
        assertThat(prefixDTO).isNotNull();
    }

    @Test
    public void groupPrefixTest() throws JustsendApiClientException {
        List<PrefixReservationDTO> groupResponses = groupService.getGroupPrefix();
        Assertions.assertThat(groupResponses).isNotNull();
        Assertions.assertThat(groupResponses).isNotEmpty();
    }

    private GroupDTO updateGroup(Long groupID) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(groupID);
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

    private GroupCreate groupCreate() {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName("name");
        groupCreate.setMembers(asList("Number1", "Number2"));
        return groupCreate;
    }


    private Long getGroupID(String group) {
        String groupId = group.split(":")[1].trim().replace(",", "");
        return valueOf(groupId);
    }
}