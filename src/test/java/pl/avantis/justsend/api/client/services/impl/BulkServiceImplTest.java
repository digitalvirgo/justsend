package pl.avantis.justsend.api.client.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.fluttercode.datafactory.impl.DataFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.BulkGroupList;
import pl.avantis.justsend.api.client.model.BulkResponse;
import pl.avantis.justsend.api.client.model.GroupCreate;
import pl.avantis.justsend.api.client.model.GroupMember;
import pl.avantis.justsend.api.client.model.GroupResponse;
import pl.avantis.justsend.api.client.model.LanguageMessage;
import pl.avantis.justsend.api.client.model.MessageStatus;
import pl.avantis.justsend.api.client.model.SenderResponse;
import pl.avantis.justsend.api.client.pojo.PostBackFileDTO;
import pl.avantis.justsend.api.client.pojo.PostBackRecipientDTO;
import pl.avantis.justsend.api.client.services.impl.services.GroupService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.avantis.justsend.api.client.test.helpers.Commands;

import java.util.List;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.GROUP_ID;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.checkIfProdUrl;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkStatus.CANCELED;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.ECO;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.ECO_RESP;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.TEST;
import static pl.avantis.justsend.api.client.test.helpers.BulkBuilder.bulkWithDefaultFieldsSet;
import static pl.avantis.justsend.api.client.test.helpers.BulkGroupListBuilder.bulkGroupListWithDefaultListSet;
import static pl.avantis.justsend.api.client.test.helpers.Constants.MOCK_API_URL;

public class BulkServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(BulkServiceImplTest.class);

    private BulkServiceImpl bulkService;
    private GroupService groupService;
    private PostBackServiceImpl postBackService;

    private DataFactory dataFactory = new DataFactory();

    @BeforeClass
    public void setUp() {
        Constants.JUSTSEND_API_URL = "http://justsend-api.dcos.staging.avantis.pl/api/rest";
        bulkService = new BulkServiceImpl(APP_KEY);
        postBackService = new PostBackServiceImpl(MOCK_API_URL);

        checkIfProdUrl();
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
    public void testBulkFlow1() throws JustsendApiClientException {
        LOGGER.info("=========   send Bulk  ===========");
        Bulk sendBulk = bulkWithDefaultFieldsSet().withGroupId(41L).build();
        BulkResponse sendBulkResponse = bulkService.sendBulk(sendBulk);
        LOGGER.info("bulkResponse = " + TestHelper.toString(sendBulkResponse));
    }

    @Test
    public void when_SendBulkWithTestGET_USER_ACK_then_bulkRecipientWillHaveStateDELIVERED() throws JustsendApiClientException, InterruptedException {
        //given
        Bulk sendBulk = bulkWithDefaultFieldsSet()
                .withTo(asList("514875" + dataFactory.getNumberText(3)))
                .withBulkVariant(ECO)
                .withMessage(format("%s:%s; Damian", Commands.Response, "dsadas")).build();
        BulkResponse bulkResponse = bulkService.sendBulk(sendBulk);

        sleep(70000); // time needed to proceed message
        //when
        List<String> bulkRecipients = bulkService.retrieveBulkRecipientsByMessageStatus(MessageStatus.DELIVERED, bulkResponse.getId());


        //then
        assertThat(bulkRecipients).contains("48" + sendBulk.getTo().get(0));
    }

    @Test
    public void when_SendBulkWithTestGETResponse_then_bulkRecipientWillBeAddedToGroup() throws JustsendApiClientException, InterruptedException {
        //given
        Bulk sendBulk = bulkWithDefaultFieldsSet()
                .withTo(asList("514875" + dataFactory.getNumberText(3)))
                .withBulkVariant(ECO_RESP)
                .withMessage(format("%s:%s; Damian", Commands.Response, "PREFIXTEST")).build();
        BulkResponse bulkResponse = bulkService.sendBulk(sendBulk);

        sleep(70000); // time needed to proceed message
        //when
        List<GroupResponse> groupResponses = groupService.retrieveGroups();

        //then
        GroupResponse groupResponse = groupResponses.get(groupResponses.size() - 1);
        assertThat(groupResponse.getMembers()).contains("48" + sendBulk.getTo().get(0));
    }

    @Test
    public void whenTestBulkPostBack() throws JustsendApiClientException, InterruptedException {
        //given
        Bulk sendBulk = bulkWithDefaultFieldsSet()
                .withTo(asList("514875" + dataFactory.getNumberText(3)))
                .withBulkVariant(ECO)
                .withMessage("Damian").build();
        BulkResponse bulkResponse = bulkService.sendBulk(sendBulk);

        sleep(80000); // time needed to proceed message

        //when
        PostBackFileDTO postBackFile = postBackService.getPostBackFile(bulkResponse.getId());

        //then
        assertThat(postBackFile).isNotNull();
        assertThat(postBackFile.getId()).isEqualTo(bulkResponse.getId());
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
        String group = groupService.createGroup(groupCreate());
        Long groupID = GroupServiceImplTest.getGroupID(group);

        LOGGER.info("=========   send Bulk Group List ===========");
        BulkGroupList bulkGroupList = bulkGroupListWithDefaultListSet().withGroupIds(asList(groupID)).build();
        BulkResponse bulkGroupListResponse = bulkService.sendBulk(bulkGroupList);
        assertThat(bulkGroupListResponse.getGroupIds()).containsExactly(groupID);
        LOGGER.info("bulkGroupListResponse = " + TestHelper.toString(bulkGroupListResponse));
    }

    private GroupCreate groupCreate() {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName("name");
        groupCreate.setMembers(asList(new GroupMember("Number1"), new GroupMember("Number2")));
        return groupCreate;
    }
}