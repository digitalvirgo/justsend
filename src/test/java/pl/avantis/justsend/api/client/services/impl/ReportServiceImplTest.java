package pl.avantis.justsend.api.client.services.impl;

import org.apache.log4j.Logger;
import org.fluttercode.datafactory.impl.DataFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.BulkResponse;
import pl.avantis.justsend.api.client.model.FileReportStatus;
import pl.avantis.justsend.api.client.model.GroupResponse;
import pl.avantis.justsend.api.client.model.Prefix;
import pl.avantis.justsend.api.client.model.ReportMessageResponse;
import pl.avantis.justsend.api.client.model.ReportResponse;
import pl.avantis.justsend.api.client.model.ReportResponseMessage;
import pl.avantis.justsend.api.client.model.ReportSubAccountResponse;
import pl.avantis.justsend.api.client.model.SingleBulkReport;
import pl.avantis.justsend.api.client.model.SubAccount;
import pl.avantis.justsend.api.client.services.impl.enums.BulkVariant;
import pl.avantis.justsend.api.client.services.impl.enums.FileReportStatuses;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.APP_KEY;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.GROUP_ID;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.checkIfProdUrl;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.daysBeforeNowLD;
import static pl.avantis.justsend.api.client.services.impl.TestHelper.getFileNamePart;
import static pl.avantis.justsend.api.client.services.impl.enums.BulkVariant.TEST;
import static pl.avantis.justsend.api.client.services.impl.enums.FileNamePartEnum.FILE_ID;
import static pl.avantis.justsend.api.client.services.impl.enums.FileReportStatuses.GENERATING;
import static pl.avantis.justsend.api.client.test.helpers.BulkBuilder.bulkWithDefaultFieldsSet;
import static pl.avantis.justsend.api.client.test.helpers.Commands.GET_RESPONSE;
import static pl.avantis.justsend.api.client.test.helpers.Commands.GET_USER_DELIVERY_ACK;
import static pl.avantis.justsend.api.client.test.helpers.DataGenerator.getRandomPhoneNumber;

public class ReportServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImplTest.class);

    private DataFactory dataFactory;

    private ReportServiceImpl reportService;
    private BulkServiceImpl bulkService;
    private AccountServiceImpl accountService;
    private MessageServiceImpl messageService;
    private GroupServiceImpl groupService;
    private PrefixServiceImpl prefixService;

    private String bulkName = "Name" + new Random().nextInt();
    private String senderSingleBulk = "senderS" + new Random().nextInt(100000);
    private BulkResponse bulkResponse;
    private Long singleBulkId;

    @BeforeClass
    public void setUp() throws JustsendApiClientException {
        reportService = new ReportServiceImpl(APP_KEY);
        checkIfProdUrl(reportService);
        bulkService = new BulkServiceImpl(APP_KEY);
        accountService = new AccountServiceImpl(APP_KEY);
        messageService = new MessageServiceImpl(APP_KEY);
        prefixService = new PrefixServiceImpl(APP_KEY);

        groupService = new GroupServiceImpl(APP_KEY);

        dataFactory = new DataFactory();

        bulkResponse = bulkService.sendBulk(bulkWithDefaultFieldsSet().withGroupId(GROUP_ID).build());
        bulkService.cancelBulkById(bulkResponse.getId());

        singleBulkId = messageService.sendMessage("48505948311", bulkResponse.getFrom(), "Justsend lib api test", BulkVariant.ECO);
    }

    @Test
    public void testGetReports() throws JustsendApiClientException, InterruptedException {
        //given
        List<GroupResponse> groupResponses = groupService.retrieveGroups();
        BulkResponse bulkResponse = bulkService.sendBulk(bulkWithDefaultFieldsSet().withGroupId(groupResponses.get(0).getGroupId()).build());

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
        //when
        ReportResponse retrieveBulkReportByBulkIdResponse = reportService.retrieveBulkReportByBulkId(bulkResponse.getId());

        //then
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
        BulkResponse bulkResponse = bulkServiceSubAccount.sendBulk(bulkWithDefaultFieldsSet().withGroupId(GROUP_ID).withName(bulkName).build());
        bulkServiceSubAccount.cancelBulkById(bulkResponse.getId());

        //when
        List<ReportSubAccountResponse> reportSubAccountResponses = reportService.retrieveReportSubAccount(subAccount.getSubid().intValue());

        //then
        assertThat(reportSubAccountResponses).filteredOn("bulkId", bulkResponse.getId()).hasSize(1);
    }

    @Test
    public void testRetrieveResponseMessages() throws JustsendApiClientException, InterruptedException {
        //given
        List<Prefix> prefixes = prefixService.retrieveAllPrefixesPagin("GLOBAL", 0, 10);
        String prefix = prefixes.get(0).getName();
        String toNumber = "48514875" + dataFactory.getNumberText(3);
        Bulk sendBulk = bulkWithDefaultFieldsSet()
                .withTo(asList(getRandomPhoneNumber()))
                .withBulkVariant(TEST)
                .withMessage(format(" %s   %s:%s   Damian", GET_USER_DELIVERY_ACK, GET_RESPONSE, prefix))
                .build();
        BulkResponse bulkResponse = bulkService.sendBulk(sendBulk);

        sleep(20000);
        //when
        List<ReportResponseMessage> retrieveResponseMessagesReportResponse = reportService.retrieveResponseMessages(prefix, daysBeforeNowLD(1), daysBeforeNowLD(-1));

        //then
        assertThat(retrieveResponseMessagesReportResponse).filteredOn("msisdn", toNumber).size().isGreaterThanOrEqualTo(1);
        LOGGER.info("retrieveResponseMessages = " + retrieveResponseMessagesReportResponse);
    }

    @Test
    public void testRetrieveSingleBulkReportByBulkId() throws JustsendApiClientException {
        //when
        ReportMessageResponse retrieveSingleBulkReportByBulkIdResponse = reportService.retrieveSingleBulkReportByBulkId(singleBulkId);

        //then
        assertThat(retrieveSingleBulkReportByBulkIdResponse.getFrom()).isEqualTo(senderSingleBulk);
    }

    @Test
    public void testRetrieveReportMessagesByDate() throws JustsendApiClientException {
        //when
        List<ReportMessageResponse> reportMessageResponses = reportService.retrieveReportMessagesByDate(daysBeforeNowLD(1), daysBeforeNowLD(-1));

        //then
        assertThat(reportMessageResponses).filteredOn("from", bulkResponse.getFrom()).size().isEqualTo(1);
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
        assertThat(singleBulkReport).filteredOn("senderSingleBulk", senderSingleBulk).hasSize(1);
        LOGGER.info("singleBulkReport = " + singleBulkReport);
    }

    @Test
    public void testRetrieveSingleBulksCountByStartDate() throws JustsendApiClientException {
        //when
        Long retrieveSingleBulksCountByStartDateResponse = reportService.retrieveSingleBulksCountByStartDate(daysBeforeNowLD(1), daysBeforeNowLD(-1), singleBulkId, senderSingleBulk);

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