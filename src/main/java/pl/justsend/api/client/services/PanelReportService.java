package pl.justsend.api.client.services;

import pl.justsend.api.client.model.ReportResponse;
import pl.justsend.api.client.model.dto.ResponseMessageDTO;
import pl.justsend.api.client.model.dto.SingleBulkReportDTO;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.time.LocalDate;
import java.util.List;

public interface PanelReportService {

    /**
     * Zwraca listę bulków do wysłania.
     *
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @return
     */

    List<ReportResponse> retrieveBulksDuringSend(
            Integer rowFrom,
            Integer rowSize) throws JustsendApiClientException;

    /**
     * Zwraca listę bulków do wysłania.
     *
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @param sort    Parametr sortowania
     * @param order   Porządek (0 - ASC, 1 - DESC)
     * @return
     */

    List<ReportResponse> retrieveBulksDuringSendPagin(
            Integer rowFrom,
            Integer rowSize,
            String sort,
            Integer order) throws JustsendApiClientException;

    /**
     * Zwraca liczbę bulków do wysłania.
     *
     * @return
     */

    Long retrieveBulksDuringSendCount() throws JustsendApiClientException;

    /**
     * @param prefixId Numer prefixu
     * @param from     Data od (yyyy-MM-dd)
     * @param to       Data do (yyyy-MM-dd)
     * @param id       Identyfikator zwrotki
     * @param prefix   Nazwa prefixu
     * @return
     */

    Long retrieveCountResponseMessages(
            Integer prefixId,
            LocalDate from,
            LocalDate to,
            Long id,
            String prefix,
            Integer startRow,
            Integer countRow) throws JustsendApiClientException;

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

    List<ResponseMessageDTO> retrieveResponseMessages(
            Integer prefixId,
            LocalDate from,
            LocalDate to,
            Integer startRow,
            Integer countRow) throws JustsendApiClientException;

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

    List<ResponseMessageDTO> retrieveResponseMessagesPagin(Integer pageNumber, Integer countRow, Integer prefixId,
                                                           LocalDate from, LocalDate to, String sort, OrderEnum order, Long id, String prefix) throws JustsendApiClientException;

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

    List<ReportResponse> retrieveBulkListByDatePagin(
            LocalDate from,
            LocalDate to,
            Integer pageNumber,
            Integer rowSize,
            String sort,
            OrderEnum order,
            Long id,
            String name,
            String sender) throws JustsendApiClientException;

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

    List<SingleBulkReportDTO> retrieveSingleBulksByStartDate(
            LocalDate from,
            LocalDate to,
            Integer rowFrom,
            Integer rowSize,
            String sort,
            OrderEnum order,
            Long id,
            String sender) throws JustsendApiClientException;
}
