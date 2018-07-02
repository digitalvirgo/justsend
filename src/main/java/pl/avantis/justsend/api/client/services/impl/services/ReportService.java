package pl.avantis.justsend.api.client.services.impl.services;

import pl.avantis.justsend.api.client.model.FileReportStatus;
import pl.avantis.justsend.api.client.model.ReportMessageResponse;
import pl.avantis.justsend.api.client.model.ReportResponse;
import pl.avantis.justsend.api.client.model.ReportResponseMessage;
import pl.avantis.justsend.api.client.model.ReportSubAccountResponse;
import pl.avantis.justsend.api.client.model.SingleBulkReport;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    /**
     * Zwraca szczegółowe informacje o masowych wysyłkach zrealizowanych w danym okresie czasu.
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to   Date to (yyyy-MM-dd)
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<ReportResponse> retrieveBulkByDate(final LocalDate from, final LocalDate to) throws JustsendApiClientException;

    /**
     * Zwraca szczegółowe informacje o masowej wysyłce na podstawie identyfikatora wysyłki.
     *
     * @param bulkId Bulk id
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    ReportResponse retrieveBulkReportByBulkId(Long bulkId) throws JustsendApiClientException;

    /**
     * Pobieranie zbiorczego raportu z wysyłek dla podużytkownika
     *
     * @param subId SubUserId
     * @return liste raportów
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<ReportSubAccountResponse> retrieveReportSubAccount(Integer subId) throws JustsendApiClientException;

    /**
     * Pobieranie listy odpowiedzi
     *
     * @param prefix Prefix
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @return lista odpowiedzi
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<ReportResponseMessage> retrieveResponseMessages(String prefix, LocalDate from, LocalDate to) throws JustsendApiClientException;

    /**
     * Zwraca informację o statusie dostarczenia wiadomości do odbiorcy.
     *
     * @param messageId Message id
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    ReportMessageResponse retrieveSingleBulkReportByBulkId(Long messageId) throws JustsendApiClientException;

    /**
     * Zwraca informację o statusie dostarczenia wiadomości do odbiorcy w danym okresie czasu.
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<ReportMessageResponse> retrieveReportMessagesByDate(LocalDate from, LocalDate to) throws JustsendApiClientException;

    /**
     * Zwraca informacje o masowych wysyłkach zrealizowanych w danym okresie czasu  w formie listy.
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<ReportResponse> retrieveBulkListByDate(LocalDate from, LocalDate to, Integer rowFrom, Integer rowSize) throws JustsendApiClientException;

    /**
     * Zwraca informacje o masowych wysyłkach zrealizowanych w danym okresie czasu  w formie listy.
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @param id Identyfikator wysyłki
     * @param name Nazwa wysyłki
     * @param sender Nazwa wysyłającego
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long retrieveBulkListCountByDate(LocalDate from, LocalDate to, Long id, String name, String sender) throws JustsendApiClientException;

    /**
     * Zwraca informacje o masowych wysyłkach zrealizowanych w danym okresie czasu  w formie listy.
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @param rowFrom Indeks startowy rekordów do pobrania
     * @param rowSize Ilość rekordów do pobrania
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<SingleBulkReport> retrieveSingleBulksByStartDate(LocalDate from, LocalDate to, Integer rowFrom, Integer rowSize) throws JustsendApiClientException;

    /**
     * Zwraca informacje o masowych wysyłkach zrealizowanych w danym okresie czasu  w formie listy.
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @param id Identyfikator wysyłki
     * @param sender Nazwa wysyłającego
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long retrieveSingleBulksCountByStartDate(LocalDate from, LocalDate to, Long id, String sender) throws JustsendApiClientException;

    /**
     * Dodaje do kolejki zadanie wygenerowania raportu historii bulków za dany okres
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @return nazwa pliku
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String generateBulkHistoryReport(LocalDate from, LocalDate to) throws JustsendApiClientException;

    /**
     * Dodaje do kolejki zadanie wygenerowania raportu historii bulków "single" za dany okres
     *
     * @param from Date from (yyyy-MM-dd)
     * @param to Date to (yyyy-MM-dd)
     * @return file name
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String generateSingleBulkHistoryReport(LocalDate from, LocalDate to) throws JustsendApiClientException;

    /**
     * Dodaje do kolejki zadanie wygenerowania raportu odbiorców bulków
     *
     * @param bulkId Bulk ID
     * @return file name
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String generateBulkRecipientsReport(Long bulkId) throws JustsendApiClientException;

    /**
     * Pobiera status generowania raportu
     *
     * @param searchKey Identyfikator pliku / parametr wyszukiwania
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    FileReportStatus getReportStatus(String searchKey) throws JustsendApiClientException;

    /**
     * Pobiera wygenerowany raport
     *
     * @param fileId Identyfikator pliku
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String getReports(String fileId) throws JustsendApiClientException;

    /**
     * Pobiera listę raportów oczekujacych w kolecje oraz wygenerowanych, znajdujących sie na zasobie
     *
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<FileReportStatus> listBulkHistoryReports() throws JustsendApiClientException;

    /**
     * Pobiera listę raportów oczekujacych w kolecje oraz wygenerowanych, znajdujących sie na zasobie
     *
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<FileReportStatus> listBulkRecipientsReports() throws JustsendApiClientException;

    /**
     * Pobiera listę raportów oczekujacych w kolecje oraz wygenerowanych, znajdujących sie na zasobie
     *
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<FileReportStatus> listSingleBulkRecipientsReports() throws JustsendApiClientException;

    /**
     * Dodaje do kolejki zadanie wygenerowania raportu historii bulków za dany okres
     *
     * @param groupId Identyfikator grupy
     * @return file name
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    String generateBulkHistoryReport(Long groupId) throws JustsendApiClientException;
}
