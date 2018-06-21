package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.model.dto.FileReportStatusDTO;
import pl.justsend.api.client.model.dto.SingleBulkReportDTO;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.services.impl.BulkServiceImplTest.sendBulk;
import static pl.justsend.api.client.test.services.BaseTest.APP_KEY;

public class ReportServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImplTest.class);

    private ReportServiceImpl reportService;
    private BulkServiceImpl bulkService;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String bulkName = "Name12345";
    private String sender = "BulkService";
    private BulkResponse bulkResponse;


    @BeforeMethod
    public void setUp() throws JustsendApiClientException {
        reportService = new ReportServiceImpl(APP_KEY);
        bulkService = new BulkServiceImpl(APP_KEY);

        bulkResponse = bulkService.sendBulk(sendBulk(bulkName, sender));
        bulkService.cancelBulkById(bulkResponse.getId());
    }

    @Test
    public void testBulkFlow() throws JustsendApiClientException {
        LOGGER.info("  ==============  retrieveBulkByDate ============== ");
        List<ReportResponse> retrieveBulkByDateResponses = reportService.retrieveBulkByDate(daysBeforeNow(1), daysBeforeNow(0));
        assertThat(retrieveBulkByDateResponses.size()).isGreaterThanOrEqualTo(1);
        assertThat(retrieveBulkByDateResponses).filteredOn("bulkName", bulkName).isNotEmpty();
        LOGGER.info("retrieveBulkByDateResponses = " + retrieveBulkByDateResponses);

//        LOGGER.info("  ==============  retrieveBulkListByDate ============== ");
//        List<ReportResponse> retrieveBulkListByDateResponses = reportService.retrieveBulkListByDate(daysBeforeNow(1), daysBeforeNow(0), 1, 10);
//        assertThat(retrieveBulkListByDateResponses).filteredOn("bulkName", bulkName).isNotEmpty();
//        LOGGER.info("retrieveBulkListByDateResponses = " + retrieveBulkListByDateResponses);

        LOGGER.info("  ==============  retrieveBulkListCountByDate ============== ");
        Long retrieveBulkListCountByDateResponse = reportService.retrieveBulkListCountByDate(daysBeforeNow(1), daysBeforeNow(0), 1L, bulkName, sender);
        assertThat(retrieveBulkListCountByDateResponse).isGreaterThan(1);
        LOGGER.info("retrieveBulkListCountByDateResponse = " + retrieveBulkListCountByDateResponse);

        LOGGER.info("  ==============  singleBulkReportDTO ============== ");
        List<SingleBulkReportDTO> singleBulkReportDTO = reportService.retrieveSingleBulksByStartDate(daysBeforeNow(1), daysBeforeNow(0), 1, 10);
        assertThat(singleBulkReportDTO).isNotNull();
        LOGGER.info("singleBulkReportDTO = " + singleBulkReportDTO);

        LOGGER.info("  ==============  retrieveSingleBulksCountByStartDate ============== ");
        Long retrieveSingleBulksCountByStartDateResponse = reportService.retrieveSingleBulksCountByStartDate(daysBeforeNow(1), daysBeforeNow(0), 1L, sender);
        LOGGER.info("retrieveSingleBulksCountByStartDateResponse = " + retrieveSingleBulksCountByStartDateResponse);

        LOGGER.info("  ==============  retrieveCountPoints ============== ");
        List<FileReportStatusDTO> fileReportStatusDTOS = reportService.listBulkHistoryReports();
//        Assertions.assertThat(fileReportStatusDTOS).filteredOn("bulkName", bulkName).isNotEmpty();
        LOGGER.info("retrieveCountPoints = " + fileReportStatusDTOS);


        LOGGER.info("  ==============  listBulkRecipientsReports ============== ");
        reportService.generateBulkRecipientsReport(bulkResponse.getId());
        List<FileReportStatusDTO> listBulkRecipientsReportsResponse = reportService.listBulkRecipientsReports();
//        Assertions.assertThat(listBulkRecipientsReportsResponse).filteredOn("bulkName", bulkName).isNotEmpty();
        LOGGER.info("listBulkRecipientsReportsResponse = " + listBulkRecipientsReportsResponse);

        LOGGER.info("  ==============  listSingleBulkRecipientsReports ============== ");
        reportService.generateSingleBulkHistoryReport(daysBeforeNow(1), daysBeforeNow(0));
        List<FileReportStatusDTO> listSingleBulkRecipientsReportsResponse = reportService.listSingleBulkRecipientsReports();
//        Assertions.assertThat(listSingleBulkRecipientsReportsResponse).filteredOn("bulkName", bulkName).isNotEmpty();
        LOGGER.info("listSingleBulkRecipientsReportsResponse = " + listSingleBulkRecipientsReportsResponse);

        LOGGER.info("  ==============  generateGroupMsisdns ============== ");
        reportService.generateGroupMsisdns(bulkResponse.getGroupIds().get(0));


        LOGGER.info("  ==============  retrieveBulkReportByBulkId ============== ");
        ReportResponse retrieveBulkReportByBulkIdResponse = reportService.retrieveBulkReportByBulkId(bulkResponse.getId());
        assertThat(retrieveBulkReportByBulkIdResponse.getBulkName()).isSubstringOf(bulkResponse.getName());
        LOGGER.info("retrieveBulkReportByBulkIdResponse = " + retrieveBulkReportByBulkIdResponse);

        LOGGER.info("  ==============  reportMessage ============== ");
        List<ReportMessageResponse> reportMessageResponses = reportService.retrieveReportMessagesByDate(daysBeforeNow(1), daysBeforeNow(0));
        LOGGER.info("reportMessageResponses = " + reportMessageResponses);

//        LOGGER.info("  ==============  reportSubAccount ============== ");
//        List<ReportSubAccountResponse> reportSubAccountResponses = reportService.retrieveReportSubAccount(12);
//        LOGGER.info("reportSubAccountResponses = " + reportSubAccountResponses);

        LOGGER.info("  ==============  retrieveRespongroupMsisdsseMessages ============== ");
        String prefix = "prefix";
        List<ResponseMessage> retrieveResponseMessagesResponse = reportService.retrieveResponseMessages(prefix, daysBeforeNow(1), daysBeforeNow(0));
        LOGGER.info("retrieveResponseMessages = " + retrieveResponseMessagesResponse);

        LOGGER.info("  ==============  retrieveSingleBulkReportByBulkId ============== ");
        ReportMessageResponse retrieveSingleBulkReportByBulkIdResponse = reportService.retrieveSingleBulkReportByBulkId(bulkResponse.getId());
        LOGGER.info("retrieveSingleBulkReportByBulkIdResponse = " + new ReflectionToStringBuilder(retrieveSingleBulkReportByBulkIdResponse, SIMPLE_STYLE));

//        reportService.getReports();
//        reportService.getReportStatus();
    }

    private String daysBeforeNow(int minusDays) {
        LocalDate localDate = LocalDate.now().minusDays(minusDays);
        return dtf.format(localDate);

    }

    @Test
    public void testRetrieveBulkReportByBulkId() {
    }

    @Test
    public void testRetrieveReportSubAccount() {
    }

    @Test
    public void testRetrieveResponseMessages() {
    }

    @Test
    public void testRetrieveSingleBulkReportByBulkId() {
    }

    @Test
    public void testRetrieveReportMessagesByDate() {
    }

    @Test
    public void testRetrieveBulkListByDate() {
    }

    @Test
    public void testRetrieveBulkListCountByDate() {
    }

    @Test
    public void testRetrieveSingleBulksByStartDate() {
    }

    @Test
    public void testRetrieveSingleBulksCountByStartDate() {
    }

    @Test
    public void testGenerateBulkHistoryReport() throws JustsendApiClientException {
        reportService.generateBulkHistoryReport("2017-10-10", "2017-10-10");
    }

    @Test
    public void testGenerateSingleBulkHistoryReport() {
    }

    @Test
    public void testGenerateBulkRecipientsReport() {
    }

    @Test
    public void testGetReportStatus() {
    }

    @Test
    public void testGetReports() {
    }

    @Test
    public void testListBulkHistoryReports() {
    }

    @Test
    public void testListBulkRecipientsReports() {
    }

    @Test
    public void testListSingleBulkRecipientsReports() {
    }

    @Test
    public void testGenerateGroupMsisdns() {
    }
}