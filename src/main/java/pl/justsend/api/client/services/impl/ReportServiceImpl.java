package pl.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.model.dto.FileReportStatusDTO;
import pl.justsend.api.client.model.dto.SingleBulkReportDTO;
import pl.justsend.api.client.services.BaseService;
import pl.justsend.api.client.services.ReportService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

public class ReportServiceImpl extends BaseService implements ReportService {

    /**
     *
     * @param appKey Klucz api
     */

    public ReportServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public List<ReportResponse> retrieveBulkByDate(final String from, final String to) throws JustsendApiClientException {
        String url = createURL("/report/{appKey}/{from}/{to}", "from", from, "to", to);

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
            return processResponse(jsResponse, new TypeToken<ReportResponse>() {
            }.getType());

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
    public List<ResponseMessage> retrieveResponseMessages(final String prefix, final String from, final String to) throws JustsendApiClientException {
        String url = createURL("/report/response/messages/{appKey}/{prefix}/{from}/{to}", "prefix", prefix, "from", from, "to", to);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ResponseMessage>>() {
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
    public List<ReportMessageResponse> retrieveReportMessagesByDate(final String from, final String to) throws JustsendApiClientException {
        String url = createURL("/report/message/{appKey}/{from}/{to}", "from", from, "to", to);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportMessageResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


    @Override
    public List<ReportResponse> retrieveBulkListByDate(final String from, final String to, final Integer rowFrom, final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/report/list/reportBulks/{appKey}/{from}/{to}/{rowFrom}/{rowSize}"
                , "from", from, "to", to, "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportMessageResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


    @Override
    public Long retrieveBulkListCountByDate(final String from, final String to, final Long id, final String name, final String sender) throws JustsendApiClientException {
        String url = createURL("/report/list/reportBulksCount/{appKey}/{from}/{to}"
                , "from", from, "to", to);
        url = addParameters(url, "id", valueOf(id), "name", name, "sender", sender);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<SingleBulkReportDTO> retrieveSingleBulksByStartDate(final String from, final String to, final Integer rowFrom, final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/report/list/reportSingleBulks/{appKey}/{from}/{to}/{rowFrom}/{rowSize}"
                , "from", from, "to", to, "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SingleBulkReportDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Long retrieveSingleBulksCountByStartDate(final String from, final String to, final Long id, final String sender) throws JustsendApiClientException {
        String url = createURL("/report/list/reportSingleBulksCount/{appKey}/{from}/{to}"
                , "from", from, "to", to);
        url = addParameters(url, "id", valueOf(id), "sender", sender);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public void generateBulkHistoryReport(final String from, final String to) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/bulkHistory/{appKey}/{from}/{to}"
                , "from", from, "to", to);
        try {

            justsendHttpClient.doGet(url);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public void generateSingleBulkHistoryReport(final String from, final String to) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/singleBulkHistory/{appKey}/{from}/{to}"
                , "from", from, "to", to);
        try {

            justsendHttpClient.doGet(url);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public void generateBulkRecipientsReport(final Long bulkId) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/bulkRecipients/{appKey}/{bulkId}"
                , "bulkId", valueOf(bulkId));
        try {

            justsendHttpClient.doGet(url);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public FileReportStatusDTO getReportStatus(final String searchKey) throws JustsendApiClientException {
        String url = createURL("/report/file/status/{appKey}/{searchKey}"
                , "searchKey", searchKey);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, FileReportStatusDTO.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public byte[] getReports(final String fileId) throws JustsendApiClientException {
        String url = createURL("/report/file/get/{appKey}/{fileId}"
                , "fileId", fileId);
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            //TODO: check if it work
            return processResponse(jsResponse, byte[].class);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<FileReportStatusDTO> listBulkHistoryReports() throws JustsendApiClientException {
        String url = createURL("/report/file/list/bulkHistory/{appKey}");
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<FileReportStatusDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<FileReportStatusDTO> listBulkRecipientsReports() throws JustsendApiClientException {
        String url = createURL("/report/file/list/bulkRecipients/{appKey}");
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<FileReportStatusDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<FileReportStatusDTO> listSingleBulkRecipientsReports() throws JustsendApiClientException {
        String url = createURL("/report/file/list/singleBulkHistory/{appKey}");
        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<FileReportStatusDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public void generateGroupMsisdns(final Long groupId) throws JustsendApiClientException {
        String url = createURL("/report/file/generate/groupMsisds/{appKey}/{groupId}", "groupId", valueOf(groupId));
        try {

            justsendHttpClient.doGet(url);
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}

