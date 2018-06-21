package pl.justsend.api.client.services;

import pl.justsend.api.client.model.ReportMessageResponse;
import pl.justsend.api.client.model.ReportResponse;
import pl.justsend.api.client.model.ReportSubAccountResponse;
import pl.justsend.api.client.model.ResponseMessage;
import pl.justsend.api.client.model.dto.FileReportStatusDTO;
import pl.justsend.api.client.model.dto.SingleBulkReportDTO;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

public interface ReportService {

    /**
     * Zwraca szczegółowe informacje o masowych wysyłkach zrealizowanych w danym okresie czasu.
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to   Date to (yyyy-MM-dd)
     * @return
     */

    List<ReportResponse> retrieveBulkByDate(String from, String to) throws JustsendApiClientException;

    /**
     * Zwraca szczegółowe informacje o masowej wysyłce na podstawie identyfikatora wysyłki.
     *
     * @param bulkId Bulk id
     * @return
     */

    ReportResponse retrieveBulkReportByBulkId(Long bulkId) throws JustsendApiClientException;

    List<ReportSubAccountResponse> retrieveReportSubAccount(Integer subId) throws JustsendApiClientException;

    List<ResponseMessage> retrieveResponseMessages(String prefix, String from, String to) throws JustsendApiClientException;

    ReportMessageResponse retrieveSingleBulkReportByBulkId(Long messageId) throws JustsendApiClientException;

    List<ReportMessageResponse> retrieveReportMessagesByDate(String from, String to) throws JustsendApiClientException;

    List<ReportResponse> retrieveBulkListByDate(String from, String to, Integer rowFrom, Integer rowSize) throws JustsendApiClientException;

    Long retrieveBulkListCountByDate(String from, String to, Long id, String name, String sender) throws JustsendApiClientException;

    List<SingleBulkReportDTO> retrieveSingleBulksByStartDate(String from, String to, Integer rowFrom, Integer rowSize) throws JustsendApiClientException;

    Long retrieveSingleBulksCountByStartDate(String from, String to, Long id, String sender) throws JustsendApiClientException;

    void generateBulkHistoryReport(String from, String to) throws JustsendApiClientException;

    void generateSingleBulkHistoryReport(String from, String to) throws JustsendApiClientException;

    void generateBulkRecipientsReport(Long bulkId) throws JustsendApiClientException;

    FileReportStatusDTO getReportStatus(String searchKey) throws JustsendApiClientException;

    byte[] getReports(String fileId) throws JustsendApiClientException;

    List<FileReportStatusDTO> listBulkHistoryReports() throws JustsendApiClientException;

    List<FileReportStatusDTO> listBulkRecipientsReports() throws JustsendApiClientException;

    List<FileReportStatusDTO> listSingleBulkRecipientsReports() throws JustsendApiClientException;

    void generateGroupMsisdns(Long groupId) throws JustsendApiClientException;
}
