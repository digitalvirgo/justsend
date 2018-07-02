package pl.avantis.justsend.api.client.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.model.BaseBulk;
import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.BulkGroupList;
import pl.avantis.justsend.api.client.model.BulkResponse;
import pl.avantis.justsend.api.client.model.GroupCreate;
import pl.avantis.justsend.api.client.model.LanguageMessage;
import pl.avantis.justsend.api.client.model.MessageStatus;
import pl.avantis.justsend.api.client.model.SenderResponse;
import pl.avantis.justsend.api.client.services.impl.enums.BulkVariant;
import pl.avantis.justsend.api.client.services.impl.services.BulkService;
import pl.avantis.justsend.api.client.services.impl.services.GroupService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.TEST;
import static pl.avantis.justsend.api.client.test.helpers.BulkBuilder.bulkWithDefaultFieldsSet;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.GROUP_ID;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkStatus.CANCELED;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.ECO;
import static pl.avantis.justsend.api.client.test.helpers.BulkGroupListBuilder.bulkGroupListWithDefaultListSet;

public class BulkServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(BulkServiceImplTest.class);

    private BulkService bulkService;
    private GroupService groupService;

    private ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @BeforeClass
    public void setUp() {
        bulkService = new BulkServiceImpl(APP_KEY);
        groupService = new GroupServiceImpl(APP_KEY);
    }

    @Test
    public void testBulkFlow() throws JustsendApiClientException {
        LOGGER.info("=========   send Bulk  ===========");
        Bulk sendBulk = bulkWithDefaultFieldsSet().withGroupId(GROUP_ID).build();
        BulkResponse sendBulkResponse = bulkService.sendBulk(sendBulk);
        assertThat(sendBulkResponse.getName()).startsWith(sendBulk.getName());
        LOGGER.info("bulkResponse = " + TestHelper.toString(sendBulkResponse));


        LOGGER.info("=========   cancel Bulk By Id ===========");
        BulkResponse cancelBulkResponse = bulkService.cancelBulkById(sendBulkResponse.getId());
        assertThat(cancelBulkResponse.getId()).isEqualTo(sendBulkResponse.getId());
        assertThat(cancelBulkResponse.getBulkStatus()).isEqualTo(CANCELED);
        LOGGER.info("cancelBulkResponse = " + TestHelper.toString(cancelBulkResponse));


        LOGGER.info("=========   retrieve Bulk By Id ===========");
        BulkResponse retrieveBulkByIdResponse = bulkService.retrieveBulkById(sendBulkResponse.getId());
        assertThat(retrieveBulkByIdResponse.getId()).isEqualTo(sendBulkResponse.getId());
        LOGGER.info("retrieveBulkByIdResponse = " + TestHelper.toString(retrieveBulkByIdResponse));

        LOGGER.info("=========   retrieve bulk recipient by message status  ===========");
        List<String> bulkRecipientsByMessageStatus = bulkService.retrieveBulkRecipientsByMessageStatus(MessageStatus.NOT_SENT, sendBulkResponse.getId());
        assertThat(bulkRecipientsByMessageStatus.size()).isEqualTo(1);
        assertThat(bulkRecipientsByMessageStatus.get(0)).endsWith(sendBulk.getTo().get(0));
        LOGGER.info("bulkRecipientsByMessageStatus = " + bulkRecipientsByMessageStatus);
    }

    @Test
    public void testSendBulkWithoutConfirmation() throws JustsendApiClientException {
        LOGGER.info("=========   send Bulk Without Confirmation  ===========");
        Bulk sendBulk = bulkWithDefaultFieldsSet().withGroupId(GROUP_ID).build();
        BulkResponse sendBulkWithoutConfirmationResponse = bulkService.sendBulkWithoutConfirmation(sendBulk);
        assertThat(sendBulkWithoutConfirmationResponse.getName()).startsWith(sendBulk.getName());
        LOGGER.info("sendBulkWithoutConfirmation = " + sendBulkWithoutConfirmationResponse);
    }

    @Test
    public void testSendPersonalizedBulk() throws JustsendApiClientException {
        Long personalizedBulk = bulkService.sendPersonalizedBulk(
                "testPersonalizedBulk", "damian", "2017-09-12T12:23:34+02:00", "ECO",
                true, LanguageMessage.POLISH, new TestHelper().getFile("personalizedMessage.txt"));
        assertThat(personalizedBulk).isGreaterThan(0L);
        LOGGER.info("personalizedBulk = " + personalizedBulk);
    }

    @Test
    public void testSendPersonalizedBulkWithoutLanguage() throws JustsendApiClientException {
        Long personalizedBulk = bulkService.sendPersonalizedBulk(
                "testPersonalizedBulk", "damian", "2017-09-12T12:23:34+02:00", "ECO",
                true, new TestHelper().getFile("personalizedMessage.txt"));
        assertThat(personalizedBulk).isGreaterThan(0L);
        LOGGER.info("personalizedBulk = " + personalizedBulk);
    }

    @Test
    public void testRetrieveAliases() throws JustsendApiClientException {
        List<SenderResponse> retrieveAliasesResponse = bulkService.retrieveAliases();
        assertThat(retrieveAliasesResponse).isNotNull();
        assertThat(retrieveAliasesResponse).isNotEmpty();
        LOGGER.info("senderResponses = " + retrieveAliasesResponse);
    }

    @Test
    public void testSendBulkGroupListFlow() throws JustsendApiClientException, JsonProcessingException {
        LOGGER.info("=========   create group ===========");
        String group = groupService.createGroup(groupCreate(), new TestHelper().getFile("emptyFile.txt"));
        Long groupID = GroupServiceImplTest.getGroupID(group);

        LOGGER.info("=========   send Bulk Group List ===========");
        BulkGroupList bulkGroupList = bulkGroupListWithDefaultListSet().withGroupIds(asList(groupID)).build();
        BulkResponse bulkGroupListResponse = bulkService.sendBulk(bulkGroupList);
        assertThat(bulkGroupListResponse.getGroupIds()).containsExactly(groupID);
        LOGGER.info("bulkGroupListResponse = " + TestHelper.toString(bulkGroupListResponse));
    }

    private String groupCreate() throws JsonProcessingException {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName("name");
        groupCreate.setMembers(asList("Number1", "Number2"));
        return OBJECT_MAPPER.writeValueAsString(groupCreate);
    }
}