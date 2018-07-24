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
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.avantis.justsend.api.client.test.helpers.DataGenerator;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Long.valueOf;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY_ADMINISTRATOR;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.checkIfProdUrl;
import static pl.avantis.justsend.api.client.services.impl.enums.PrefixAccessType.GLOBAL;
import static pl.avantis.justsend.api.client.test.helpers.DataGenerator.getRandomPhoneNumber;

public class GroupServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(GroupServiceImplTest.class);
    private GroupServiceImpl groupService;
    private PrefixServiceImpl prefixService;
    private Long groupID;
    private static final Random random = new Random();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static GroupCreate groupCreate() {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName("name" + random.nextInt(1000));
        return groupCreate;
    }

    public static Long getGroupID(String group) {
        String groupId = group.split(":")[1].trim().replace(",", "");
        return valueOf(groupId);
    }

    @BeforeMethod
    public void setUp() throws JustsendApiClientException {

        groupService = new GroupServiceImpl(APP_KEY);

        checkIfProdUrl(groupService);
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
    public void when_SendRequestToCreateEmptyGroup_then_createEmptyGroup() throws JustsendApiClientException {
        //when
        String group = groupService.createGroup(groupCreate(), new TestHelper().getFile("emptyFile.txt"));
        assertThat(group).startsWith("Created group id: ");

        //then
        Long groupID = GroupServiceImplTest.getGroupID(group);
        Group groupRetrieve = groupService.retrieveGroup(groupID);
        assertThat(groupRetrieve.getMembers()).isEmpty();
    }

    @Test
    public void when_SendRequestToCreateGroupWithTwoNumbers_then_createGroupWithTwoNumbers() throws JustsendApiClientException {
        //given
        GroupCreate groupCreate = groupCreate();
        groupCreate.setMembers(asList(getRandomPhoneNumber(), getRandomPhoneNumber()));

        //when
        String group = groupService.createGroup(groupCreate, new TestHelper().getFile("emptyFile.txt"));
        assertThat(group).startsWith("Created group id: ");

        //then
        Long groupID = GroupServiceImplTest.getGroupID(group);
        Group groupRetrieve = groupService.retrieveGroup(groupID);
        assertThat(groupRetrieve.getMembers()).hasSize(2);
    }

    @Test
    public void when_SendRequestToCreateGroupWithTwoNumbersInFile_then_createGroupWithTwoNumbers() throws JustsendApiClientException, InterruptedException {
        //when
        String group = groupService.createGroup(groupCreate(), new TestHelper().getFile("groupMisin.txt"));
        assertThat(group).startsWith("Created group id: ");

        sleep(60000);
        //then
        Long groupID = GroupServiceImplTest.getGroupID(group);
        Group groupRetrieve = groupService.retrieveGroup(groupID);
        assertThat(groupRetrieve.getMembers()).hasSize(1);
        assertThat(groupRetrieve.getMembers()).contains("48514132134");
    }


    @Test
    public void testCreateGroupWithoutFile() throws JustsendApiClientException, JsonProcessingException {
        //when
        String group = groupService.createGroup(groupCreate());

        //then
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
    public void testAddNumberToGroup() throws JustsendApiClientException, InterruptedException {
        //given
        GroupUpdate addNumberToGroupRequest = addNumberToGroupRequest(groupID);

        //when
        sleep(70000);
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

    //Not work

    @Test
    public void testAddMsisdnToGroup() throws JustsendApiClientException, InterruptedException {
        //when
        String groupResponses = groupService.addNumbersToGroup(groupID, new TestHelper().getFile("groupMisin.txt"));

        //then
        Assertions.assertThat(groupResponses).isEqualTo("Successful");

        sleep(70000); // time to process file
        Group group = groupService.retrieveGroup(groupID);
        assertThat(group.getMembers()).containsExactlyInAnyOrder("48514132134");
    }

    @Test
    public void testUpdateGroup1() throws JustsendApiClientException {
        //given
        String number = "514673908";

        //when
        GroupDTO updateGroupRequest = updateGroup(groupID, number);
        GroupDTO updateGroupResponse = groupService.updateGroup(updateGroupRequest);

        //then
        Assertions.assertThat(updateGroupResponse.getMembers().get(2).getMsisdn()).isEqualTo(number);
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