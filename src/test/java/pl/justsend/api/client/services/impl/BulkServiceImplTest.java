package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.services.BulkService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;
import static pl.justsend.api.client.model.enums.BulkVariant.ECO;
import static pl.justsend.api.client.test.services.BaseTest.APP_KEY;

public class BulkServiceImplTest {

    private static Logger LOGGER = Logger.getLogger(BulkServiceImplTest.class);

    private BulkService bulkService;

    @BeforeClass
    public void setUp() {
        bulkService = new BulkServiceImpl(APP_KEY);
    }

    @Test
    public void testBulkFlow() throws JustsendApiClientException {
        LOGGER.info("=========   send Bulk  ===========");
        Bulk sendBulk = sendBulk();
        BulkResponse sendBulkResponse = bulkService.sendBulk(sendBulk);
        Assertions.assertThat(sendBulkResponse).isNotNull();
        LOGGER.info("bulkResponse = " + new ReflectionToStringBuilder(sendBulkResponse, ToStringStyle.SHORT_PREFIX_STYLE));

//        LOGGER.info("=========   send Bulk Group List ===========");
//        BulkGroupList bulkGroupList = sendBulkGroupList();
//        BulkResponse bulkGroupListResponse = bulkService.sendBulk(bulkGroupList);
//        LOGGER.info("bulkGroupListResponse = " + new ReflectionToStringBuilder(bulkGroupListResponse, SIMPLE_STYLE));

        LOGGER.info("=========   cancel Bulk By Id ===GroupManager========");
        BulkResponse cancelBulkResponse = bulkService.cancelBulkById(sendBulkResponse.getId());
        Assertions.assertThat(cancelBulkResponse).isNotNull();
        LOGGER.info("cancelBulkResponse = " + new ReflectionToStringBuilder(cancelBulkResponse, SIMPLE_STYLE));

        LOGGER.info("=========   retrieve Bulk By Id ===========");
        BulkResponse retrieveBulkByIdResponse = bulkService.retrieveBulkById(sendBulkResponse.getId());
        Assertions.assertThat(retrieveBulkByIdResponse).isNotNull();
        LOGGER.info("retrieveBulkByIdResponse = " + new ReflectionToStringBuilder(retrieveBulkByIdResponse, SIMPLE_STYLE));

        LOGGER.info("=========   retrieve aliases ===========");
        List<SenderResponse> senderResponses = bulkService.retrieveAliases();
        Assertions.assertThat(senderResponses).isNotNull();
        LOGGER.info("senderResponses = " + new ReflectionToStringBuilder(senderResponses, SIMPLE_STYLE));

        LOGGER.info("=========   retrieve bulk recipient by message status  ===========");
        List<String> bulkRecipientsByMessageStatus = bulkService.retrieveBulkRecipientsByMessageStatus(MessageStatus.NOT_SENT, sendBulkResponse.getId());
        Assertions.assertThat(bulkRecipientsByMessageStatus).isNotNull();
        LOGGER.info("bulkRecipientsByMessageStatus = " + bulkRecipientsByMessageStatus);

        LOGGER.info("=========   retrieve bulk recipient by message status  ===========");
        Long personalizedBulk = bulkService.sendPersonalizedBulk(
                "testPersonalizedBulk", "damian", "2017-09-12T12:23:34+02:00", "ECO",
                true, LanguageMessage.POLISH, new TestHelper().getFile("addressFile.txt"));
        LOGGER.info("personalizedBulk = " + personalizedBulk);
    }

    private BulkGroupList sendBulkGroupList() {
        BulkGroupList bulkGroupList =new BulkGroupList();
        setBaseBulk(bulkGroupList);
        bulkGroupList.setGroupIds(asList(29L, 113L));
        return bulkGroupList;
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

}