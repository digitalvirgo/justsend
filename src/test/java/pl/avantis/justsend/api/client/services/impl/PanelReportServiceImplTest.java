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

        Bulk sendBulk = bulkWithDefaultFieldsSet().withGroupId(GROUP_ID).build();
        bulkResponse = bulkService.sendBulk(sendBulk);
    }

    @Test
    public void testRetrieveBulksDuringSend() throws JustsendApiClientException {
        List<ReportResponse> reportResponses = panelReportService.retrieveBulksDuringSend(0, 10);
        assertThat(reportResponses).filteredOn("bulkId", bulkResponse.getId()).hasSize(1);
    }


    //TODO Działa raz dziala raz nie RMI problems :/
    //java.lang.ClassNotFoundException: org.springframework.dao.InvalidDataAccessApiUsageException (no security manager: RMI cla
    //ss loader disabled)
    @Test
    public void testRetrieveBulksDuringSendPagin() throws JustsendApiClientException {
        List<ReportResponse> reportResponses = panelReportService.retrieveBulksDuringSendPagin(0, 100, null, OrderEnum.ASC);

    }

    @Test
    public void testRetrieveBulksDuringSendCount() throws JustsendApiClientException {
        Long retrieveBulksDuringSendCount = panelReportService.retrieveBulksDuringSendCount();
        assertThat(retrieveBulksDuringSendCount).isPositive();
    }

    @Test
    public void testRetrieveCountResponseMessages() throws JustsendApiClientException {
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

        //when
        Long retrieveCountResponse = panelReportService.retrieveCountResponseMessages(
                prefix.getId(), daysBeforeNowLD(1), daysBeforeNowLD(0), null, prefix.getName());

        //then
        assertThat(retrieveCountResponse).isEqualTo(0);
    }

    @Test
    public void testRetrieveResponseMessages() throws JustsendApiClientException {
        //TODO create more meaningful test, when will be able to send response messages
        List<PanelReportResponseMessage> panelReportResponseMessages = panelReportService.retrieveResponseMessages(1, daysBeforeNowLD(1), daysBeforeNowLD(0), 0, MAX_VALUE);
        assertThat(panelReportResponseMessages).isEmpty();
    }

    @Test
    public void testRetrieveResponseMessagesPagin() throws JustsendApiClientException {
        //TODO create more meaningful test, when will be able to send response messages
        List<PanelReportResponseMessage> panelReportResponseMessages = panelReportService.retrieveResponseMessagesPagin(
                1, 10, 1, daysBeforeNowLD(1), daysBeforeNowLD(0),
                null, OrderEnum.ASC, 1L, "prefix");
        assertThat(panelReportResponseMessages).isEmpty();
    }

    //TODO Działa raz dziala raz nie RMI problems :/
    //java.lang.ClassNotFoundException: org.springframework.dao.InvalidDataAccessApiUsageException (no security manager: RMI cla
    //ss loader disabled)
    @Test
    public void testRetrieveBulkListByDatePagin() throws JustsendApiClientException {
        List<ReportResponse> reportResponses = panelReportService.retrieveBulkListByDatePagin(daysBeforeNowLD(1), daysBeforeNowLD(0), 1, 10, null, OrderEnum.ASC, bulkResponse.getId(), bulkResponse.getName(), bulkResponse.getFrom());
        assertThat(reportResponses).isEmpty();
    }

    @Test
    public void testRetrieveSingleBulksByStartDate() throws JustsendApiClientException, InterruptedException {
        //given
        //TODO why dose not retrieve any message
        String sender = "sender" + new Random().nextInt(100000);
        Long singleBulkId = messageService.sendMessage("48505948311", sender, "Justsend lib api test", BulkVariant.ECO);

        sleep(30000);
        //when
        List<SingleBulkReport> singleBulkReports = panelReportService.retrieveSingleBulksByStartDate(daysBeforeNowLD(1), daysBeforeNowLD(-1), 0, 10, null, OrderEnum.ASC, singleBulkId, sender);

        //then
        assertThat(singleBulkReports).hasSize(1);
    }
}