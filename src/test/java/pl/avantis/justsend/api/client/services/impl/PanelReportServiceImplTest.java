package pl.avantis.justsend.api.client.services.impl;

import org.fluttercode.datafactory.impl.DataFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.BulkResponse;
import pl.avantis.justsend.api.client.model.PanelReportResponseMessage;
import pl.avantis.justsend.api.client.model.Prefix;
import pl.avantis.justsend.api.client.model.ReportResponse;
import pl.avantis.justsend.api.client.model.SingleBulkReport;
import pl.avantis.justsend.api.client.services.impl.enums.BulkVariant;
import pl.avantis.justsend.api.client.services.impl.enums.OrderEnum;
import pl.avantis.justsend.api.client.services.impl.services.MessageService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.avantis.justsend.api.client.test.helpers.DataGenerator;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.*;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.GROUP_ID;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.daysBeforeNowLD;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.TEST;
import static pl.avantis.justsend.api.client.test.helpers.BulkBuilder.bulkWithDefaultFieldsSet;
import static pl.avantis.justsend.api.client.test.helpers.Commands.GET_RESPONSE;
import static pl.avantis.justsend.api.client.test.helpers.Commands.GET_USER_DELIVERY_ACK;

public class PanelReportServiceImplTest {

    private PanelReportServiceImpl panelReportService;
    private BulkServiceImpl bulkService;
    private BulkResponse bulkResponse;
    private MessageService messageService;
    private PrefixServiceImpl prefixService;
    private DataFactory dataFactory;

    @BeforeClass
    public void setUp() throws JustsendApiClientException {
        panelReportService = new PanelReportServiceImpl(APP_KEY_ADMINISTRATOR);
        checkIfProdUrl(panelReportService);
        bulkService = new BulkServiceImpl(APP_KEY_ADMINISTRATOR);
        messageService = new MessageServiceImpl(APP_KEY);
        prefixService = new PrefixServiceImpl(APP_KEY);

        dataFactory = new DataFactory();

        Bulk sendBulk = bulkWithDefaultFieldsSet().build();
        bulkResponse = bulkService.sendBulk(sendBulk);
    }

    @Test
    public void testRetrieveBulksDuringSend() throws JustsendApiClientException {
        List<ReportResponse> reportResponses = panelReportService.retrieveBulksDuringSend(0, 10);
        assertThat(reportResponses).filteredOn("bulkId", bulkResponse.getId()).hasSize(1);
    }


    @Test
    public void testRetrieveBulksDuringSendPagin() throws JustsendApiClientException {
        //when
        List<ReportResponse> reportResponses = panelReportService.retrieveBulksDuringSendPagin(0, 100, "name", OrderEnum.ASC);

        //then
        assertThat(reportResponses).
                filteredOn("bulkId", bulkResponse.getId())
                .size().isEqualTo(1);
    }

    @Test
    public void testRetrieveBulksDuringSendCount() throws JustsendApiClientException {
        Long retrieveBulksDuringSendCount = panelReportService.retrieveBulksDuringSendCount();
        assertThat(retrieveBulksDuringSendCount).isPositive();
    }

    @Test
    public void testRetrieveCountResponseMessages() throws JustsendApiClientException, InterruptedException {
        //given
        List<Prefix> prefixes = prefixService.retrieveAllPrefixesPagin("GLOBAL", 0, 10);
        Prefix prefix = prefixes.get(0);
        String toNumber = "48514875" + dataFactory.getNumberText(3);
        Bulk sendBulk = bulkWithDefaultFieldsSet()
                .withTo(asList(toNumber))
                .withBulkVariant(TEST)
                .withMessage(format(" %s   %s:%s   Damian", GET_USER_DELIVERY_ACK, GET_RESPONSE, prefix))
                .build();
        BulkResponse bulkResponse = bulkService.sendBulk(sendBulk);

        sleep(25000);
        //when
        Long retrieveCountResponse = panelReportService.retrieveCountResponseMessages(
                prefix.getId(), daysBeforeNowLD(1), daysBeforeNowLD(-1), bulkResponse.getId(), prefix.getName());


        //then
        assertThat(retrieveCountResponse).isEqualTo(1);
    }

    @Test
    public void testRetrieveResponseMessages() throws JustsendApiClientException, InterruptedException {
        //given
        List<Prefix> prefixes = prefixService.retrieveAllPrefixesPagin("GLOBAL", 0, 10);
        Prefix prefix = prefixes.get(0);
        String toNumber = "48514875" + dataFactory.getNumberText(3);
        Bulk sendBulk = bulkWithDefaultFieldsSet()
                .withTo(asList(toNumber))
                .withBulkVariant(TEST)
                .withMessage(format(" %s   %s:%s   Damian", GET_USER_DELIVERY_ACK, GET_RESPONSE, prefix))
                .build();
        BulkResponse bulkResponse = bulkService.sendBulk(sendBulk);

        sleep(25000);
        //when
        List<PanelReportResponseMessage> panelReportResponseMessages = panelReportService.retrieveResponseMessages(
                prefix.getId(), daysBeforeNowLD(1), daysBeforeNowLD(-1), 0, MAX_VALUE);


        //then
        assertThat(panelReportResponseMessages)
                .filteredOn("msisdn", toNumber)
                .size().isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testRetrieveResponseMessagesPagin() throws JustsendApiClientException, InterruptedException {
        //given
        List<Prefix> prefixes = prefixService.retrieveAllPrefixesPagin("GLOBAL", 0, 10);
        Prefix prefix = prefixes.get(0);
        String toNumber = "48514875" + dataFactory.getNumberText(3);
        Bulk sendBulk = bulkWithDefaultFieldsSet()
                .withTo(asList(toNumber))
                .withBulkVariant(TEST)
                .withMessage(format(" %s   %s:%s   Damian", GET_USER_DELIVERY_ACK, GET_RESPONSE, prefix))
                .build();
        BulkResponse bulkResponse = bulkService.sendBulk(sendBulk);

        sleep(25000);

        //when
        List<PanelReportResponseMessage> panelReportResponseMessages = panelReportService.retrieveResponseMessagesPagin(
                0, MAX_VALUE, prefix.getId(), daysBeforeNowLD(1), daysBeforeNowLD(-1),
                null, OrderEnum.ASC, bulkResponse.getId(), prefix.getName());
        assertThat(panelReportResponseMessages)
                .filteredOn("msisdn", toNumber)
                .size().isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testRetrieveBulkListByDatePagin() throws JustsendApiClientException {
        //when
        List<ReportResponse> reportResponses = panelReportService.retrieveBulkListByDatePagin(daysBeforeNowLD(1), daysBeforeNowLD(-1),
                0, MAX_VALUE, "name", OrderEnum.ASC, bulkResponse.getId(), bulkResponse.getName(), bulkResponse.getFrom());

        //then
        assertThat(reportResponses).filteredOn("bulkId", bulkResponse.getId()).size().isEqualTo(0);
    }

    @Test
    public void testRetrieveSingleBulksByStartDate() throws JustsendApiClientException, InterruptedException {
        //given
        //TODO why dose not retrieve any message
        String sender = "sender" + new Random().nextInt(100000);
        Long singleBulkId = messageService.sendMessage("48505948312", sender, "Justsend jar api test", BulkVariant.ECO);

//        for (int i = 0; i < 120; i++) {
//            messageService.sendMessage(DataGenerator.getRandomPhoneNumber(), "sender" + new Random().nextInt(100000)
//                    , "Justsend jar api test", BulkVariant.ECO);
//        }

//        sleep(30000);
        //when
        List<SingleBulkReport> singleBulkReports = panelReportService.retrieveSingleBulksByStartDate(daysBeforeNowLD(1), daysBeforeNowLD(-1),
                0, MAX_VALUE, "id", OrderEnum.ASC, singleBulkId, sender);

        //then
        assertThat(singleBulkReports).hasSize(1);
    }
}