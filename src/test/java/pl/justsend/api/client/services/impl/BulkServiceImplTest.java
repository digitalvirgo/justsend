package pl.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.services.BulkService;
import pl.justsend.api.client.services.GroupService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

import static java.util.Arrays.asList;
import static pl.justsend.api.client.model.enums.BulkStatus.CANCELED;
import static pl.justsend.api.client.model.enums.BulkVariant.ECO;
import static pl.justsend.api.client.services.impl.BaseTest.APP_KEY;
import static pl.justsend.api.client.services.impl.GroupServiceImplTest.getGroupID;

public class BulkServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(BulkServiceImplTest.class);

    private BulkService bulkService;
    private GroupService groupService;

    @BeforeClass
    public void setUp() {
        bulkService = new BulkServiceImpl(APP_KEY);
        groupService= new GroupServiceImpl(APP_KEY);
    }

    @Test
    public void testBulkFlow() throws JustsendApiClientException {
        LOGGER.info("=========   send Bulk  ===========");
        Bulk sendBulk = sendBulk();
        BulkResponse sendBulkResponse = bulkService.sendBulk(sendBulk);
        Assertions.assertThat(sendBulkResponse.getName()).startsWith(sendBulk.getName());
        LOGGER.info("bulkResponse = " + TestHelper.toString(sendBulkResponse));


        LOGGER.info("=========   cancel Bulk By Id ===========");
        BulkResponse cancelBulkResponse = bulkService.cancelBulkById(sendBulkResponse.getId());
        Assertions.assertThat(cancelBulkResponse.getId()).isEqualTo(sendBulkResponse.getId());
        Assertions.assertThat(cancelBulkResponse.getBulkStatus()).isEqualTo(CANCELED);
        LOGGER.info("cancelBulkResponse = " + TestHelper.toString(cancelBulkResponse));


        LOGGER.info("=========   retrieve Bulk By Id ===========");
        BulkResponse retrieveBulkByIdResponse = bulkService.retrieveBulkById(sendBulkResponse.getId());
        Assertions.assertThat(retrieveBulkByIdResponse.getId()).isEqualTo(sendBulkResponse.getId());
        LOGGER.info("retrieveBulkByIdResponse = " + TestHelper.toString(retrieveBulkByIdResponse));


        LOGGER.info("=========   retrieve aliases ===========");
        List<SenderResponse> senderResponses = bulkService.retrieveAliases();
        Assertions.assertThat(senderResponses).isNotNull();
        Assertions.assertThat(senderResponses).isNotEmpty();
        LOGGER.info("senderResponses = " + senderResponses);


        LOGGER.info("=========   retrieve bulk recipient by message status  ===========");
        List<String> bulkRecipientsByMessageStatus = bulkService.retrieveBulkRecipientsByMessageStatus(MessageStatus.NOT_SENT, sendBulkResponse.getId());
        Assertions.assertThat(bulkRecipientsByMessageStatus.size()).isEqualTo(1);
        Assertions.assertThat(bulkRecipientsByMessageStatus.get(0)).endsWith(sendBulk.getTo().get(0));
        LOGGER.info("bulkRecipientsByMessageStatus = " + bulkRecipientsByMessageStatus);

        LOGGER.info("=========   send Bulk Without Confirmation  ===========");
        BulkResponse sendBulkWithoutConfirmationResponse = bulkService.sendBulkWithoutConfirmation(sendBulk);
        Assertions.assertThat(sendBulkWithoutConfirmationResponse.getName()).startsWith(sendBulk.getName());
        LOGGER.info("sendBulkWithoutConfirmation = " + sendBulkWithoutConfirmationResponse);

        LOGGER.info("=========   send Personalized Bulk  ===========");
        Long personalizedBulk = bulkService.sendPersonalizedBulk(
                "testPersonalizedBulk", "damian", "2017-09-12T12:23:34+02:00", "ECO",
                true, LanguageMessage.POLISH, new TestHelper().getFile("personalizedMessage.txt"));
        Assertions.assertThat(personalizedBulk).isGreaterThan(0L);
        LOGGER.info("personalizedBulk = " + personalizedBulk);
    }

    @Test
    public void testSendBulkGroupListFlow() throws JustsendApiClientException {
        LOGGER.info("=========   create group ===========");
        GroupCreate createGroupRequest = groupCreate();
        String group = groupService.createGroup(createGroupRequest);
        Long groupID = getGroupID(group);

        LOGGER.info("=========   send Bulk Group List ===========");
        BulkGroupList bulkGroupList = createBulkGroupListRequest(groupID);
        BulkResponse bulkGroupListResponse = bulkService.sendBulk(bulkGroupList);
        Assertions.assertThat(bulkGroupListResponse).isNotNull();
        LOGGER.info("bulkGroupListResponse = " + TestHelper.toString(bulkGroupListResponse));

    }

    private GroupCreate groupCreate() {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName("name");
        groupCreate.setMembers(asList("Number1", "Number2"));
        return groupCreate;
    }

    private BulkGroupList createBulkGroupListRequest(Long groupID) {
        BulkGroupList bulkGroupList =new BulkGroupList();
        setBaseBulk(bulkGroupList);
        bulkGroupList.setGroupIds(asList(groupID));
        return bulkGroupList;
    }

    public static Bulk sendBulk() {
        Bulk bulk = new Bulk();
        setBaseBulk(bulk);
        bulk.setGroupId(2393L);
        return bulk;
    }

    public static Bulk sendBulk(String name, String sender) {
        Bulk bulk = sendBulk();
        bulk.setName(name);
        bulk.setFrom(sender);
        return bulk;
    }

    private static void setBaseBulk(BaseBulk bulk){
        bulk.setName("Name12345");
        bulk.setMessage("Test message");
        bulk.setBulkVariant(ECO);
        bulk.setFrom("BulkService");
        bulk.setTo(asList("514746368"));
        bulk.setLanguage(LanguageMessage.POLISH);
        bulk.setSendDate("2018-06-20T16:54:67-00:00");
    }

}