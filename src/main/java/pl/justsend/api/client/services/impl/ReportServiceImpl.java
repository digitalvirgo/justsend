package pl.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.services.ReportService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.lang.String.valueOf;
import static pl.justsend.api.client.http.utils.DateUtils.convertDate;

public class ReportServiceImpl extends BaseService implements ReportService {

    /**
     * @param appKey Klucz api
     */

    public ReportServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public List<ReportResponse> retrieveBulkByDate(final LocalDate from, final LocalDate to) throws JustsendApiClientException {
        String url = createURL("/report/{appKey}/{from}/{to}", "from", convertDate(from), "to", convertDate(to));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public ReportResponse retrieveBulkReportByBulkId(final Long bulkId) throws JustsendApiClientException {
        String url = createURL("/report/{appKey}/{bulkId}", "bulkId", valueOf(bulkId));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, ReportResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public List<ReportSubAccountResponse> retrieveReportSubAccount(final Integer subId) throws JustsendApiClientException {
        String url = createURL("/report/subaccount/{appKey}/{subId}", "subId", valueOf(subId));

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, null);
            return processResponse(jsResponse, new TypeToken<List<ReportSubAccountResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<ReportResponseMessage> retrieveResponseMessages(final String prefix, final LocalDate from, final LocalDate to) throws JustsendApiClientException {
        String url = createURL("/report/response/messages/{appKey}/{prefix}/{from}/{to}", "prefix", prefix, "from", convertDate(from), "to", convertDate(to));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportResponseMessage>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public ReportMessageResponse retrieveSingleBulkReportByBulkId(final Long messageId) throws JustsendApiClientException {
        String url = createURL("/report/message/{appKey}/{messageId}", "messageId", valueOf(messageId));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, ReportMessageResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<ReportMessageResponse> retrieveReportMessagesByDate(final LocalDate from, final LocalDate to) throws JustsendApiClientException {
        String url = createURL("/report/message/{appKey}/{from}/{to}", "from", convertDate(from), "to", convertDate(to));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportMessageResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


    @Override
    public List<ReportResponse> retrieveBulkListByDate(final LocalDate from, final LocalDate to, final Integer rowFrom, final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/report/list/reportBulks/{appKey}/{from}/{to}/{rowFrom}/{rowSize}"
                , "from", convertDate(from), "to", convertDate(to), "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


    @Override
    public Long retrieveBulkListCountByDate(final LocalDate from, final LocalDate to, final Long id, final String name, final String sender) throws JustsendApiClientException {
        String url = createURL("/report/list/reportBulksCount/{appKey}/{from}/{to}"
                , "from", convertDate(from), "to", convertDate(to));
        url = addParameters(url, "id", valueOf(id), "name", name, "sender", sender);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<SingleBulkReport> retrieveSingleBulksByStartDate(final LocalDate from, final LocalDate to, final Integer rowFrom, final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/report/list/reportSingleBulks/{appKey}/{from}/{to}/{rowFrom}/{rowSize}"
                , "from", convertDate(from), "to", convertDate(to), "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SingleBulkReport>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Long retrieveSingleBulksCountByStartDate(final LocalDate from, final LocalDate to, final Long id, final String sender) throws JustsendApiClientException {
        String url = createURL("/report/list/reportSingleBulksCount/{appKey}/{from}/{to}"
                , "from", convertDate(from), "to", convertDate(to));
        url = addParameters(url, "id", valueOf(id), "sender", sender);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String generateBulkHistoryReport(final LocalDate from, final LocalDate to) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/bulkHistory/{appKey}/{from}/{to}"
                , "from", convertDate(from), "to", convertDate(to));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, String.class);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String generateSingleBulkHistoryReport(final LocalDate from, final LocalDate to) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/singleBulkHistory/{appKey}/{from}/{to}"
                , "from", convertDate(from), "to", convertDate(to));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, String.class);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String generateBulkRecipientsReport(final Long bulkId) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/bulkRecipients/{appKey}/{bulkId}"
                , "bulkId", valueOf(bulkId));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, String.class);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public FileReportStatus getReportStatus(final String searchKey) throws JustsendApiClientException {
        String url = createURL("/report/file/status/{appKey}/{searchKey}"
                , "searchKey", searchKey);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, FileReportStatus.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String getReports(final String fileId) throws JustsendApiClientException {
        String url = createURL("/report/file/get/{appKey}/{fileId}"
                , "fileId", fileId);
        try {

            return justsendHttpClient.doGetByte(url);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<FileReportStatus> listBulkHistoryReports() throws JustsendApiClientException {
        String url = createURL("/report/file/list/bulkHistory/{appKey}");
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<FileReportStatus>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<FileReportStatus> listBulkRecipientsReports() throws JustsendApiClientException {
        String url = createURL("/report/file/list/bulkRecipients/{appKey}");
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<FileReportStatus>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<FileReportStatus> listSingleBulkRecipientsReports() throws JustsendApiClientException {
        String url = createURL("/report/file/list/singleBulkHistory/{appKey}");
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<FileReportStatus>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String generateBulkHistoryReport(final Long groupId) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/groupMsisds/{appKey}/{groupId}", "groupId", valueOf(groupId));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, String.class);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}

