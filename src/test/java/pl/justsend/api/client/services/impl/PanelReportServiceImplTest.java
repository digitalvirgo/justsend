package pl.justsend.api.client.services.impl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.model.enums.OrderEnum;
import pl.justsend.api.client.services.MessageService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.justsend.api.client.services.impl.TestHelper.APP_KEY_ADMINISTRATOR;
import static pl.justsend.api.client.services.impl.TestHelper.daysBeforeNowLD;

public class PanelReportServiceImplTest {

    private PanelReportServiceImpl panelReportService;
    private BulkServiceImpl bulkService;
    private BulkResponse bulkResponse;
    private MessageService messageService;

    @BeforeClass
    public void setUp() throws JustsendApiClientException {
        panelReportService = new PanelReportServiceImpl(APP_KEY_ADMINISTRATOR);
        bulkService = new BulkServiceImpl(APP_KEY_ADMINISTRATOR);
        messageService = new MessageServiceImpl(APP_KEY);

        Bulk sendBulk = BulkServiceImplTest.createSendBulk();
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
        //TODO create more meaningful test, when will be able to send response messages
        Long retrieveCountResponse = panelReportService.retrieveCountResponseMessages(1, daysBeforeNowLD(1), daysBeforeNowLD(0), 1L, "prefix", 1, 1);
        assertThat(retrieveCountResponse).isEqualTo(0);
    }

    @Test
    public void testRetrieveResponseMessages() throws JustsendApiClientException {
        //TODO create more meaningful test, when will be able to send response messages
        List<PanelReportResponseMessage> panelReportResponseMessages = panelReportService.retrieveResponseMessages(1, daysBeforeNowLD(1), daysBeforeNowLD(0), 1, 10);
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