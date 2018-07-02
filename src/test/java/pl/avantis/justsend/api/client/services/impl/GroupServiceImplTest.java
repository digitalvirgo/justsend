package pl.avantis.justsend.api.client.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.model.Group;
import pl.avantis.justsend.api.client.model.GroupCreate;
import pl.avantis.justsend.api.client.model.GroupDTO;
import pl.avantis.justsend.api.client.model.GroupMember;
import pl.avantis.justsend.api.client.model.GroupResponse;
import pl.avantis.justsend.api.client.model.GroupUpdate;
import pl.avantis.justsend.api.client.model.Prefix;
import pl.avantis.justsend.api.client.model.PrefixReservation;
import pl.avantis.justsend.api.client.services.impl.enums.PrefixType;
import pl.avantis.justsend.api.client.services.impl.services.GroupService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Long.valueOf;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY_ADMINISTRATOR;
import static pl.avantis.justsend.api.client.services.impl.enums.PrefixAccessType.GLOBAL;

public class GroupServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(GroupServiceImplTest.class);
    private GroupService groupService;
    private PrefixServiceImpl prefixService;
    private Long groupID;
    private static final Random random = new Random();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String groupCreate() throws JsonProcessingException {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName("name" + random.nextInt(1000));
        groupCreate.setMembers(asList("Number1", "Number2"));
        return OBJECT_MAPPER.writeValueAsString(groupCreate);
    }

    public static Long getGroupID(String group) {
        String groupId = group.split(":")[1].trim().replace(",", "");
        return valueOf(groupId);
    }

    @BeforeMethod
    public void setUp() throws JustsendApiClientException, JsonProcessingException {

        groupService = new GroupServiceImpl(APP_KEY);
        prefixService = new PrefixServiceImpl(APP_KEY_ADMINISTRATOR);

        String group = groupService.createGroup(groupCreate(), new TestHelper().getFile("emptyFile.txt"));
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
    public void testCreateGroup() throws JustsendApiClientException, JsonProcessingException {
        String group = groupService.createGroup(groupCreate(), new TestHelper().getFile("emptyFile.txt"));
        assertThat(group).startsWith("Created group id: ");
    }

    @Test
    public void testRemoveGroup() throws Exception {
        //when
        String removeGroup = groupService.removeGroup(groupID);

        //then
        assertThat(removeGroup).startsWith("Removed group id:");
        try {
            groupService.retrieveGroup(groupID);
        } catch (JustsendApiClientException e) {
            if (!e.getMessage().equals("NOT_FOUND - Not found")) {
                throw new Exception("Check why retrive method did not return NOT_FOUND - Not found.");
            }
        }
    }

    @Test
    public void testAddNumberToGroup() throws JustsendApiClientException {
        //given
        GroupUpdate addNumberToGroupRequest = addNumberToGroupRequest(groupID);

        //when
        String addNumberToGroup = groupService.addNumberToGroup(addNumberToGroupRequest);

        //then
        assertThat(addNumberToGroup).isEqualTo("Added: 1 numbers");
        Group group = groupService.retrieveGroup(groupID);
        assertThat(group.getMembers()).containsExactlyInAnyOrder("Number1", "Number2", "514746372");
    }

    @Test
    public void testRemoveNumbersFromGroup1() throws JustsendApiClientException {
        //given
        GroupUpdate removeNumbersFromGroupRequest = removeNumbersFromGroup(groupID);

        //when
        String removeNumbersFromGroup = groupService.removeNumbersFromGroup(removeNumbersFromGroupRequest);

        //then
        assertThat(removeNumbersFromGroup).isEqualTo("Removed: 2 numbers");
        Group group = groupService.retrieveGroup(groupID);
        assertThat(group.getMembers()).isEmpty();
    }

    @Test
    public void testAddMsisdnToGroup() throws JustsendApiClientException, InterruptedException {
        //TODO: why it adds two times same number
        //when
        String groupResponses = groupService.addNumbersToGroup(groupID, new TestHelper().getFile("groupMisin.txt"));

        //then
        Assertions.assertThat(groupResponses).isEqualTo("Successful");
//        sleep(15000);
//        Group group = groupService.retrieveGroup(groupID);
//        assertThat(group.getMembers()).containsExactlyInAnyOrder("Number1", "Number2","514132134");
    }

    @Test
    public void testUpdateGroup1() throws JustsendApiClientException {
        //given
        String number = "514673908";

        //when
        GroupDTO updateGroupRequest = updateGroup(groupID, number);
        GroupDTO updateGroupResponse = groupService.updateGroup(updateGroupRequest);


        //TODO The request sent by the client was syntactically incorrect. od 26/07/2018
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

    @Test(enabled = false)
    public void reservationPrefixTest() throws JustsendApiClientException {
        //test dosen't works, because prefix is reserved for 72h
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
        prefix.setPrefixType(PrefixType.VERIFY_COMMAND);
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