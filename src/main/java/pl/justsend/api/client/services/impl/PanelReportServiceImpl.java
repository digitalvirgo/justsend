package pl.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.model.ReportResponse;
import pl.justsend.api.client.model.dto.ResponseMessageDTO;
import pl.justsend.api.client.model.dto.SingleBulkReportDTO;
import pl.justsend.api.client.services.BaseService;
import pl.justsend.api.client.services.PanelReportService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

public class PanelReportServiceImpl extends BaseService implements PanelReportService {

    public PanelReportServiceImpl(String appKey) {
        super(appKey);
    }

    /**
     * Zwraca listę bulków do wysłania.
     *
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @return
     */

    @Override
    public List<ReportResponse> retrieveBulksDuringSend(
            final Integer rowFrom,
            final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/wwwReport/bulksDuringSend/{appKey}/{rowFrom}/{rowSize}", "rowFrom", valueOf(rowFrom),"rowSize", valueOf(rowSize));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    /**
     * Zwraca listę bulków do wysłania.
     *
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @param sort    Parametr sortowania
     * @param order   Porządek (0 - ASC, 1 - DESC)
     * @return
     */

    @Override
    public List<ReportResponse> retrieveBulksDuringSendPagin(
            final Integer rowFrom,
            final Integer rowSize,
            final String sort,
            final Integer order) throws JustsendApiClientException {
        String url = createURL("/wwwReport/bulksDuringSendPagin/{appKey}/{rowFrom}/{rowSize}", "rowFrom", valueOf(rowFrom),"rowSize", valueOf(rowSize));
        url = addParameters(url, "sort", sort, "order", valueOf(order));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    /**
     * Zwraca liczbę bulków do wysłania.
     *
     * @return
     */

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

    /**
     * @param prefixId Numer prefixu
     * @param from     Data od (yyyy-MM-dd)
     * @param to       Data do (yyyy-MM-dd)
     * @param id       Identyfikator zwrotki
     * @param prefix   Nazwa prefixu
     * @return
     */


    @Override
    public Long retrieveCountResponseMessages(
            final Integer prefixId,
            final String from,
            final String to,
            final Long id,
            final String prefix) throws JustsendApiClientException {
        String url = createURL("/wwwReport/retrieveResponseMessages/{appKey}");
        url = addParameters(url, "prefixId", valueOf(prefixId), "from", from, "to", to, "id", valueOf(id), "prefix", prefix);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ResponseMessageDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    /**
     * Zwraca wszystkie wiadomości zwrotne
     *
     * @param prefixId Numer prefixu
     * @param from     Data od
     * @param to       Data do
     * @param startRow Początek
     * @param countRow Koniec
     * @return
     */

    @Override
    public List<ResponseMessageDTO> retrieveResponseMessages(
            final Integer prefixId,
            final String from,
            final String to,
            final Integer startRow,
            final Integer countRow) throws JustsendApiClientException {
        String url = createURL("/wwwReport/retrieveResponseMessages/{appKey}");
        url = addParameters(url, "prefixId", valueOf(prefixId), "from", from, "to", to, "startRow", valueOf(startRow), "countRow", valueOf(countRow));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<ResponseMessageDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    /**
     * Zwraca wszystkie wiadomości zwrotne
     *
     * @param pageNumber Numer strony
     * @param countRow   "Koniec"
     * @param prefixId   Numer prefixu
     * @param from       Data od
     * @param to         Data do
     * @param sort       Element sotowania
     * @param order      Porządek (0 - ASC, 1 - DESC)
     * @param id         Identyfikator zwrotki
     * @param prefix     Nazwa prefixu
     * @return
     */

    @Override
    public List<ResponseMessageDTO> retrieveResponseMessagesPagin(final Integer pageNumber, final Integer countRow, final Integer prefixId,
                                                                  final String from, final String to, final String sort, final Integer order, final Long id, final String prefix) throws JustsendApiClientException {
        String url = createURL("/wwwReport/retrieveResponseMessagesPagin/{appKey}/{pageNumber}/{countRow}",
                "pageNumber", valueOf(pageNumber), "countRow", valueOf(countRow));
        url = addParameters(url, "from", from, "to", to, "prefixId", valueOf(prefixId), "sort", sort, "order", valueOf(order), "id", valueOf(id), "prefix", valueOf(prefix));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SingleBulkReportDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    /**
     * Zwraca informacje o masowych wysyłkach zrealizowanych w danym okresie czasu  w formie listy.
     *
     * @param from       Date from (yyyy-MM-dd)
     * @param to         Date to (yyyy-MM-dd)
     * @param pageNumber Indeks startowy rekordów do pobrania
     * @param rowSize    Ilość rekordów do pobrania
     * @param sort       Element sotowania
     * @param order      Porządek (0 - ASC, 1 - DESC)
     * @param id         Unikalny numer wysyłki
     * @param name       Nazwa wysyłki
     * @param sender     "Nadawca"
     * @return
     */

    @Override
    public List<ReportResponse> retrieveBulkListByDatePagin(
            final String from,
            final String to,
            final Integer pageNumber,
            final Integer rowSize,
            final String sort,
            final Integer order,
            final Long id,
            final String name,
            final String sender) throws JustsendApiClientException {
        String url = createURL("/wwwReport/list/reportBulksPagin/{appKey}/{from}/{to}/{pageNumber}/{rowSize}",
                "from", from, "to", to, "pageNumber", valueOf(pageNumber), "rowSize", valueOf(rowSize));
        url = addParameters(url, "name", name, "sort", sort, "order", valueOf(order), "id", valueOf(id), "sender", sender);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SingleBulkReportDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    /**
     * Zwraca informacje o masowych wysyłkach zrealizowanych w danym okresie czasu w formie listy.
     *
     * @param from    Date from (yyyy-MM-dd)
     * @param to      Date to (yyyy-MM-dd)
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @param sort    Parametr sortowania
     * @param order   Porządek (0 - ASC, 1 - DESC)
     * @param id      Identyfikator wysyłki
     * @param sender  Nazwa wysyłającego
     * @return
     */

    @Override
    public List<SingleBulkReportDTO> retrieveSingleBulksByStartDate(
            final String from,
            final String to,
            final Integer rowFrom,
            final Integer rowSize,
            final String sort,
            final Integer order,
            final Long id,
            final String sender) throws JustsendApiClientException {
        String url = createURL("/wwwReport/list/reportSingleBulks/{appKey}/{from}/{to}/{rowFrom}/{rowSize}",
                "from", from, "to", to, "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));
        url = addParameters(url, "sort", sort, "order", valueOf(order), "id", valueOf(id), "sender", sender);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SingleBulkReportDTO>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
