package pl.digitalvirgo.justsend.api.client.services.impl;

import org.fluttercode.datafactory.impl.DataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.digitalvirgo.justsend.api.client.model.Bulk;
import pl.digitalvirgo.justsend.api.client.model.BulkGroupList;
import pl.digitalvirgo.justsend.api.client.model.BulkResponse;
import pl.digitalvirgo.justsend.api.client.model.GroupResponse;
import pl.digitalvirgo.justsend.api.client.model.LanguageMessage;
import pl.digitalvirgo.justsend.api.client.model.MessageStatus;
import pl.digitalvirgo.justsend.api.client.model.SenderResponse;
import pl.digitalvirgo.justsend.api.client.pojo.PostBackFileDTO;
import pl.digitalvirgo.justsend.api.client.services.impl.services.GroupService;
import pl.digitalvirgo.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.digitalvirgo.justsend.api.client.test.helpers.BaseServiceHelper;
import pl.digitalvirgo.justsend.api.client.test.helpers.Commands;
import pl.digitalvirgo.justsend.api.client.test.helpers.TestHelper;

import java.util.List;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkStatus.CANCELED;
import static pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant.ECO;
import static pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant.ECO_RESP;
import static pl.digitalvirgo.justsend.api.client.test.helpers.TestConstants.MOCK_API_URL;
import static pl.digitalvirgo.justsend.api.client.test.helpers.TestHelper.compare;
import static pl.digitalvirgo.justsend.api.client.test.helpers.TestHelper.getGroupID;
import static pl.digitalvirgo.justsend.api.client.test.helpers.builder.BulkBuilder.bulkWithDefaultFieldsSet;
import static pl.digitalvirgo.justsend.api.client.test.helpers.builder.BulkGroupListBuilder.bulkGroupListWithDefaultListSet;
import static pl.digitalvirgo.justsend.api.client.test.helpers.builder.GroupCreateBuilder.aGroupCreateWithDefaults;

public class BulkServiceImplTest  extends BaseServiceHelper {

    private static Logger LOGGER = LoggerFactory.getLogger(BulkServiceImplTest.class);

    private BulkServiceImpl bulkService;
    private GroupService groupService;
    private PostBackServiceImpl postBackService;

    private DataFactory dataFactory = new DataFactory();

    private Long groupID;

    @BeforeClass
    public void setUp() {
        init();
        bulkService = new BulkServiceImpl(TestHelper.APP_KEY);
        postBackService = new PostBackServiceImpl(MOCK_API_URL);

        TestHelper.checkIfProdUrl();

        groupService = new GroupServiceImpl(TestHelper.APP_KEY);
        String group = groupService.createGroup(aGroupCreateWithDefaults().build());
        groupID = getGroupID(group);
    }

    @Test
    public void testBulkFlow() throws JustsendApiClientException {
        LOGGER.info("=========   send Bulk  ===========");
        Bulk sendBulk = bulkWithDefaultFieldsSet().withGroupId(groupID).build();

        BulkResponse sendBulkResponse = bulkService.sendBulk(sendBulk);

        compare(sendBulk, sendBulkResponse);


        LOGGER.info("=========   cancel Bulk By Id ===========");
        BulkResponse cancelBulkResponse = bulkService.cancelBulkById(sendBulkResponse.getId());

        assertThat(cancelBulkResponse.getId()).isEqualTo(sendBulkResponse.getId());
        assertThat(cancelBulkResponse.getBulkStatus()).isEqualTo(CANCELED);


        LOGGER.info("=========   retrieve Bulk By Id ===========");
        BulkResponse retrieveBulkByIdResponse = bulkService.retrieveBulkById(sendBulkResponse.getId());

        compare(sendBulk, retrieveBulkByIdResponse);

        LOGGER.info("=========   retrieve bulk recipient by message status  ===========");
        List<String> bulkRecipientsByMessageStatus = bulkService.retrieveBulkRecipientsByMessageStatus(MessageStatus.NOT_SENT, sendBulkResponse.getId());

        assertThat(bulkRecipientsByMessageStatus.size()).isEqualTo(3);
        assertThat(bulkRecipientsByMessageStatus.get(0)).endsWith(sendBulk.getTo().get(0));
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

        sleep(120000); // time needed to proceed message

        //when
        PostBackFileDTO postBackFile = postBackService.getPostBackFile(bulkResponse.getId());

        //then
        assertThat(postBackFile).isNotNull();
        assertThat(postBackFile.getId()).isEqualTo(bulkResponse.getId());
    }

    @Test
    public void testSendBulkWithoutConfirmation() throws JustsendApiClientException {
        Bulk request = bulkWithDefaultFieldsSet().withGroupId(groupID).build();

        BulkResponse response = bulkService.sendBulkWithoutConfirmation(request);

        TestHelper.compare(request, response);
    }

    @Test
    public void testSendPersonalizedBulk() throws JustsendApiClientException {
        Long personalizedBulk = bulkService.sendPersonalizedBulk(
                "testPersonalizedBulk", "damian", "2017-09-12T12:23:34+02:00", "ECO",
                true, LanguageMessage.POLISH, new TestHelper().getFile("personalizedMessage.txt"));
        assertThat(personalizedBulk).isGreaterThan(0L);
        LOGGER.info("personalizedBulk = " + personalizedBulk);
    }

    @Test(enabled = false)
    public void testSendPersonalizedBulkManyNumbers() throws JustsendApiClientException {
        String name = "testPersonalizedBulkv1ść";
        String from = "DamZian";
        Long personalizedBulk = bulkService.sendPersonalizedBulk(
                name, from, "2017-09-12T12:23:34+02:00", "ECO",
                true, LanguageMessage.POLISH, new TestHelper().getFile("10kDBTest.txt"));
        assertThat(personalizedBulk).isGreaterThan(0L);

        BulkResponse response = bulkService.retrieveBulkById(personalizedBulk);

        assertThat(response.getName()).startsWith(name);
        assertThat(response.getMessage()).isEqualTo("Wiadomość tekstowa");
        assertThat(response.getBulkVariant()).isEqualTo(ECO);
        assertThat(response.getFrom()).isEqualTo(from);
    }

    @Test
    public void testSendPersonalizedBulkWithoutLanguage() throws JustsendApiClientException, InterruptedException {
        String name = "testPersonalizedBulkv1ść";
        String from = "DamZian";
        Long personalizedBulk = bulkService.sendPersonalizedBulk(
                name, from, "2017-09-12T12:23:34+02:00", "ECO",
                true, new TestHelper().getFile("personalizedMessage.txt"));
        assertThat(personalizedBulk).isGreaterThan(0L);

        sleep(60000);
    }

    @Test
    public void testRetrieveAliases() throws JustsendApiClientException {
        List<SenderResponse> retrieveAliasesResponse = bulkService.retrieveAliases();
        assertThat(retrieveAliasesResponse).isNotNull();
        assertThat(retrieveAliasesResponse).isNotEmpty();
        LOGGER.info("senderResponses = " + retrieveAliasesResponse);
    }

    @Test
    public void testSendBulkGroupListFlow() throws JustsendApiClientException {
        LOGGER.info("=========   create group ===========");
        String group = groupService.createGroup(aGroupCreateWithDefaults().build());
        Long groupID = getGroupID(group);

        LOGGER.info("=========   send Bulk Group List ===========");
        BulkGroupList bulkGroupList = bulkGroupListWithDefaultListSet().withGroupIds(asList(groupID)).build();
        BulkResponse bulkGroupListResponse = bulkService.sendBulk(bulkGroupList);
        assertThat(bulkGroupListResponse.getGroupIds()).containsExactly(groupID);
        LOGGER.info("bulkGroupListResponse = " + TestHelper.toString(bulkGroupListResponse));
    }
}