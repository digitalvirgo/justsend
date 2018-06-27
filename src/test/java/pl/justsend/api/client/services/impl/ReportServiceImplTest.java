package pl.justsend.api.client.services.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.model.enums.FileReportStatuses;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.justsend.api.client.model.enums.FileNamePartEnum.FILE_ID;
import static pl.justsend.api.client.model.enums.FileReportStatuses.GENERATING;
import static pl.justsend.api.client.services.impl.BulkServiceImplTest.createSendBulk;
import static pl.justsend.api.client.services.impl.BulkServiceImplTest.createSendBulkToGroup;
import static pl.justsend.api.client.services.impl.TestHelper.*;

public class ReportServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImplTest.class);

    private ReportServiceImpl reportService;
    private BulkServiceImpl bulkService;
    private AccountServiceImpl accountService;
    private MessageServiceImpl messageService;
    private GroupServiceImpl groupService;

    private String bulkName = "Name" + new Random().nextInt();
    private String sender = "sender" + new Random().nextInt(100000);
    private BulkResponse bulkResponse;
    private Long singleBulkId;

    @BeforeClass
    public void setUp() throws JustsendApiClientException {
        reportService = new ReportServiceImpl(APP_KEY);
        bulkService = new BulkServiceImpl(APP_KEY);
        accountService = new AccountServiceImpl(APP_KEY);
        messageService = new MessageServiceImpl(APP_KEY);

        groupService = new GroupServiceImpl(APP_KEY);

        bulkResponse = bulkService.sendBulk(createSendBulk(bulkName, sender));
        bulkService.cancelBulkById(bulkResponse.getId());

        singleBulkId = messageService.sendMessage("48505948311", sender, "Justsend lib api test", BulkVariant.ECO);
    }

    @Test
    public void testGetReports() throws JustsendApiClientException, InterruptedException {
        //given
        List<GroupResponse> groupResponses = groupService.retrieveGroups();
        BulkResponse bulkResponse = bulkService.sendBulk(createSendBulkToGroup(groupResponses.get(0).getGroupId()));

        sleep(35000);
        String fileName = reportService.generateBulkHistoryReport(groupResponses.get(0).getGroupId());
        waitTillReportWillBeReady(fileName);
        String fileId = getFileNamePart(fileName, FILE_ID);

        //when
        String reports = reportService.getReports(fileId);

        //then
        assertThat(reports).isBlank();
    }

    @Test
    public void testRetrieveBulkByDate() throws JustsendApiClientException {

        List<ReportResponse> retrieveBulkByDateResponses = reportService.retrieveBulkByDate(daysBeforeNowLD(1), daysBeforeNowLD(0));

        assertThat(retrieveBulkByDateResponses.size()).isGreaterThanOrEqualTo(1);
        assertThat(retrieveBulkByDateResponses).filteredOn("bulkName", bulkName).hasSize(1);
        LOGGER.info("retrieveBulkByDateResponses = " + retrieveBulkByDateResponses);
    }

    @Test
    public void testRetrieveBulkReportByBulkId() throws JustsendApiClientException {

        ReportResponse retrieveBulkReportByBulkIdResponse = reportService.retrieveBulkReportByBulkId(bulkResponse.getId());

        assertThat(retrieveBulkReportByBulkIdResponse.getBulkName()).isSubstringOf(bulkResponse.getName());
        LOGGER.info("retrieveBulkReportByBulkIdResponse = " + retrieveBulkReportByBulkIdResponse);
    }

    @Test
    public void testRetrieveReportSubAccount() throws JustsendApiClientException {
        //given
        List<SubAccount> subAccounts = accountService.retrieveSubAccountsList();
        assertThat(subAccounts).isNotEmpty().overridingErrorMessage("Need subAccount to test this functionality.");
        SubAccount subAccount = subAccounts.get(0);

        BulkServiceImpl bulkServiceSubAccount = new BulkServiceImpl(subAccount.getAppKey());
        BulkResponse bulkResponse = bulkServiceSubAccount.sendBulk(createSendBulk(bulkName, sender));
        bulkServiceSubAccount.cancelBulkById(bulkResponse.getId());

        //when
        List<ReportSubAccountResponse> reportSubAccountResponses = reportService.retrieveReportSubAccount(subAccount.getSubid().intValue());

        //then
        assertThat(reportSubAccountResponses).filteredOn("bulkId", bulkResponse.getId()).hasSize(1);
    }

    @Test
    public void testRetrieveResponseMessages() throws JustsendApiClientException {
        String prefix = "prefix";
        //TODO add more meaningful test when will be possibility to create response messages
        List<ReportResponseMessage> retrieveResponseMessagesReportResponse = reportService.retrieveResponseMessages(prefix, daysBeforeNowLD(1), daysBeforeNowLD(0));
        LOGGER.info("retrieveResponseMessages = " + retrieveResponseMessagesReportResponse);
    }

    @Test
    public void testRetrieveSingleBulkReportByBulkId() throws JustsendApiClientException {
        //when
        ReportMessageResponse retrieveSingleBulkReportByBulkIdResponse = reportService.retrieveSingleBulkReportByBulkId(singleBulkId);

        //then
        assertThat(retrieveSingleBulkReportByBulkIdResponse.getFrom()).isEqualTo(sender);
        LOGGER.info("retrieveSingleBulkReportByBulkIdResponse = " + new ReflectionToStringBuilder(retrieveSingleBulkReportByBulkIdResponse, SIMPLE_STYLE));
    }

    @Test
    public void testRetrieveReportMessagesByDate() throws JustsendApiClientException {
        //when
        List<ReportMessageResponse> reportMessageResponses = reportService.retrieveReportMessagesByDate(daysBeforeNowLD(1), daysBeforeNowLD(0));

        //TODO: why in this method is retrieveSingleBulksByStartDate(loginDTO,
        //                    loginDTO.getUsername(), startDate, endDate, rowFrom: 0, rowSize: 0) rowFrom??, rowSize??
        //then
        assertThat(reportMessageResponses).isNotEmpty();
        LOGGER.info("reportMessageResponses = " + reportMessageResponses);
    }

    @Test
    public void testRetrieveBulkListByDate() throws JustsendApiClientException {
        //when
        List<ReportResponse> retrieveBulkListByDateResponses = reportService.retrieveBulkListByDate(daysBeforeNowLD(1), daysBeforeNowLD(0), 0, 100);
        //then
        assertThat(retrieveBulkListByDateResponses).filteredOn("bulkName", bulkName).hasSize(1);
        LOGGER.info("retrieveBulkListByDateResponses = " + retrieveBulkListByDateResponses);
    }

    @Test
    public void testRetrieveBulkListCountByDate() throws JustsendApiClientException {
        //when
        Long retrieveBulkListCountByDateResponse = reportService.retrieveBulkListCountByDate(daysBeforeNowLD(1), daysBeforeNowLD(0), bulkResponse.getId(), bulkResponse.getName(), bulkResponse.getFrom());

        //then
        assertThat(retrieveBulkListCountByDateResponse).isGreaterThanOrEqualTo(1);
        LOGGER.info("retrieveBulkListCountByDateResponse = " + retrieveBulkListCountByDateResponse);
    }

    @Test
    public void testRetrieveSingleBulksByStartDate() throws JustsendApiClientException {
        //when
        List<SingleBulkReport> singleBulkReport = reportService.retrieveSingleBulksByStartDate(daysBeforeNowLD(1), daysBeforeNowLD(0), 0, 100);

        //then
        assertThat(singleBulkReport).filteredOn("sender", sender).hasSize(1);
        LOGGER.info("singleBulkReport = " + singleBulkReport);
    }

    @Test
    public void testRetrieveSingleBulksCountByStartDate() throws JustsendApiClientException {
        //when
        Long retrieveSingleBulksCountByStartDateResponse = reportService.retrieveSingleBulksCountByStartDate(daysBeforeNowLD(1), daysBeforeNowLD(-1), singleBulkId, sender);

        //TODO: why it dosen't  return any result, entity is in database??
        //then
        assertThat(retrieveSingleBulksCountByStartDateResponse).isEqualTo(1);
        LOGGER.info("retrieveSingleBulksCountByStartDateResponse = " + retrieveSingleBulksCountByStartDateResponse);
    }

    @Test
    public void testGetReportStatus() throws JustsendApiClientException {
        //given
        String bulkReportFileName = reportService.generateBulkHistoryReport(daysBeforeNowLD(1), daysBeforeNowLD(0));
        String generatedFileId = getFileNamePart(bulkReportFileName, FILE_ID);

        //when
        FileReportStatus reportStatus = reportService.getReportStatus(generatedFileId);

        //then
        //TODO:why reportStatus don't return file name, looks like bug
        assertThat(reportStatus.getFileId()).isEqualTo(generatedFileId);
        assertThat(reportStatus.getFileReportStatus()).isNotNull();
    }

    @Test
    public void testListBulkHistoryReports() throws JustsendApiClientException, InterruptedException {
        //given
        String bulkHistoryReportFileName = reportService.generateBulkHistoryReport(daysBeforeNowLD(1), daysBeforeNowLD(0));
        waitTillReportWillBeReady(bulkHistoryReportFileName);
        String generatedFileId = getFileNamePart(bulkHistoryReportFileName, FILE_ID);

        //when
        List<FileReportStatus> fileReportStatuses = reportService.listBulkHistoryReports();

        //then
        assertThat(fileReportStatuses).filteredOn("fileId", generatedFileId).hasSize(1);
        LOGGER.info("listBulkHistoryReports = " + fileReportStatuses);
    }

    @Test
    public void testListBulkRecipientsReports() throws JustsendApiClientException, InterruptedException {
        //given
        String bulkRecipientsReportFileName = reportService.generateBulkRecipientsReport(bulkResponse.getId());
        waitTillReportWillBeReady(bulkRecipientsReportFileName);

        //when
        List<FileReportStatus> listBulkRecipientsReportsResponse = reportService.listBulkRecipientsReports();

        //then
        assertThat(listBulkRecipientsReportsResponse).filteredOn("fileName", bulkRecipientsReportFileName).hasSize(1);
    }

    @Test
    public void testListSingleBulkRecipientsReports() throws JustsendApiClientException, InterruptedException {
        //given
        String singleBulkHistoryReportFileName = reportService.generateSingleBulkHistoryReport(daysBeforeNowLD(1), daysBeforeNowLD(0));
        waitTillReportWillBeReady(singleBulkHistoryReportFileName);

        //when
        List<FileReportStatus> listSingleBulkRecipientsReportsResponse = reportService.listSingleBulkRecipientsReports();

        //then
        assertThat(listSingleBulkRecipientsReportsResponse).filteredOn("fileName", singleBulkHistoryReportFileName).hasSize(1);
        LOGGER.info("listSingleBulkRecipientsReportsResponse = " + listSingleBulkRecipientsReportsResponse);
    }

    private void waitTillReportWillBeReady(String fileName) throws JustsendApiClientException, InterruptedException {
        FileReportStatuses fileReportStatus = GENERATING;

        while (fileReportStatus == GENERATING) {
            sleep(1000);
            FileReportStatus reportStatus = reportService.getReportStatus(getFileNamePart(fileName, FILE_ID));
            fileReportStatus = reportStatus.getFileReportStatus();
        }
    }
}