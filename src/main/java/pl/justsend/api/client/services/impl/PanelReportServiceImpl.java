package pl.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.model.PanelReportResponseMessage;
import pl.justsend.api.client.model.ReportResponse;
import pl.justsend.api.client.model.SingleBulkReport;
import pl.justsend.api.client.model.enums.OrderEnum;
import pl.justsend.api.client.services.PanelReportService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.lang.String.valueOf;
import static pl.justsend.api.client.http.utils.DateUtils.convertDate;

public class PanelReportServiceImpl extends BaseService implements PanelReportService {

    /**
     * @param appKey Klucz api
     */

    public PanelReportServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public List<ReportResponse> retrieveBulksDuringSend(
            final Integer rowFrom,
            final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/wwwReport/bulksDuringSend/{appKey}/{rowFrom}/{rowSize}", "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }


    @Override
    public List<ReportResponse> retrieveBulksDuringSendPagin(
            final Integer rowFrom,
            final Integer rowSize,
            final String sort,
            final OrderEnum order) throws JustsendApiClientException {
        String url = createURL("/wwwReport/bulksDuringSendPagin/{appKey}/{rowFrom}/{rowSize}", "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));
        url = addParameters(url, "sort", sort, "order", order.getOrder());

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Long retrieveBulksDuringSendCount() throws JustsendApiClientException {
        String url = createURL("/wwwReport/bulksDuringSendCount/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Long retrieveCountResponseMessages(
            final Integer prefixId,
            final LocalDate from,
            final LocalDate to,
            final Long id,
            final String prefix,
            final Integer startRow,
            final Integer countRow) throws JustsendApiClientException {
        String url = createURL("/wwwReport/retrieveCountResponseMessages/{appKey}");
        url = addParameters(url, "prefixId", valueOf(prefixId), "from", convertDate(from), "to", convertDate(to), "id", valueOf(id), "prefix", prefix, "startRow", valueOf(startRow), "countRow", valueOf(countRow));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<PanelReportResponseMessage> retrieveResponseMessages(
            final Integer prefixId,
            final LocalDate from,
            final LocalDate to,
            final Integer startRow,
            final Integer countRow) throws JustsendApiClientException {
        String url = createURL("/wwwReport/retrieveResponseMessages/{appKey}");
        url = addParameters(url, "prefixId", valueOf(prefixId), "from", convertDate(from), "to", convertDate(to), "startRow", valueOf(startRow), "countRow", valueOf(countRow));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<PanelReportResponseMessage>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<PanelReportResponseMessage> retrieveResponseMessagesPagin(final Integer pageNumber, final Integer countRow, final Integer prefixId,
                                                                          final LocalDate from, final LocalDate to, final String sort, final OrderEnum order, final Long id, final String prefix) throws JustsendApiClientException {
        String url = createURL("/wwwReport/retrieveResponseMessagesPagin/{appKey}/{pageNumber}/{countRow}",
                "pageNumber", valueOf(pageNumber), "countRow", valueOf(countRow));
        url = addParameters(url, "from", convertDate(from), "to", convertDate(to), "prefixId", valueOf(prefixId), "sort", sort, "order", order.getOrder(), "id", valueOf(id), "prefix", valueOf(prefix));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<PanelReportResponseMessage>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<ReportResponse> retrieveBulkListByDatePagin(
            final LocalDate from,
            final LocalDate to,
            final Integer pageNumber,
            final Integer rowSize,
            final String sort,
            final OrderEnum order,
            final Long id,
            final String name,
            final String sender) throws JustsendApiClientException {
        String url = createURL("/wwwReport/list/reportBulksPagin/{appKey}/{from}/{to}/{pageNumber}/{rowSize}",
                "from", convertDate(from), "to", convertDate(to), "pageNumber", valueOf(pageNumber), "rowSize", valueOf(rowSize));
        url = addParameters(url, "name", name, "sort", sort, "order", order.getOrder(), "id", valueOf(id), "sender", sender);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ReportResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<SingleBulkReport> retrieveSingleBulksByStartDate(
            final LocalDate from,
            final LocalDate to,
            final Integer rowFrom,
            final Integer rowSize,
            final String sort,
            final OrderEnum order,
            final Long id,
            final String sender) throws JustsendApiClientException {
        String url = createURL("/wwwReport/list/reportSingleBulks/{appKey}/{from}/{to}/{rowFrom}/{rowSize}",
                "from", convertDate(from), "to", convertDate(to), "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));
        url = addParameters(url, "sort", sort, "order", order.getOrder(), "id", valueOf(id), "sender", sender);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SingleBulkReport>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
