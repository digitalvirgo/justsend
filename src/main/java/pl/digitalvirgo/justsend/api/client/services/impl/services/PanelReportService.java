package pl.digitalvirgo.justsend.api.client.services.impl.services;

import pl.avantis.justsend.api.client.model.PanelReportResponseMessage;
import pl.avantis.justsend.api.client.model.ReportResponse;
import pl.avantis.justsend.api.client.model.SingleBulkReport;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.OrderEnum;
import pl.digitalvirgo.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.time.LocalDate;
import java.util.List;

public interface PanelReportService {

    /**
     * Zwraca listę bulków do wysłania.
     *
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
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
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<ReportResponse> retrieveBulksDuringSendPagin(
            Integer rowFrom,
            Integer rowSize,
            String sort,
            OrderEnum order) throws JustsendApiClientException;

    /**
     * Zwraca liczbę wiadomości do wysłania.
     *
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long retrieveBulksDuringSendCount() throws JustsendApiClientException;

    /**
     * @param prefixId Numer prefixu
     * @param from     Data od (yyyy-MM-dd)
     * @param to       Data do (yyyy-MM-dd)
     * @param id       Identyfikator zwrotki
     * @param prefix   Nazwa prefixu
     * @return liczbe wiadomości zwrotnych
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long retrieveCountResponseMessages(
            Integer prefixId,
            LocalDate from,
            LocalDate to,
            Long id,
            String prefix) throws JustsendApiClientException;

    /**
     * Zwraca wszystkie wiadomości zwrotne
     *
     * @param prefixId Numer prefixu
     * @param from     Data od
     * @param to       Data do
     * @param startRow Początek
     * @param countRow Koniec
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<PanelReportResponseMessage> retrieveResponseMessages(
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
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<PanelReportResponseMessage> retrieveResponseMessagesPagin(Integer pageNumber, Integer countRow, Integer prefixId,
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
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
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
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<SingleBulkReport> retrieveSingleBulksByStartDate(
            LocalDate from,
            LocalDate to,
            Integer rowFrom,
            Integer rowSize,
            String sort,
            OrderEnum order,
            Long id,
            String sender) throws JustsendApiClientException;
}
