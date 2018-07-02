package pl.avantis.justsend.api.client.services.impl.services;

import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.BulkGroupList;
import pl.avantis.justsend.api.client.model.BulkResponse;
import pl.avantis.justsend.api.client.model.LanguageMessage;
import pl.avantis.justsend.api.client.model.MessageStatus;
import pl.avantis.justsend.api.client.model.SenderResponse;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.File;
import java.util.List;

public interface BulkService {

    /**
     * Zwraca informację o masowej wysyłce na podtstawie id wysyłki
     *
     * @param bulkId Id wysyłki
     * @return BulkResponse
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    BulkResponse retrieveBulkById(Long bulkId) throws JustsendApiClientException;

    /**
     * Zwraca listę odbiorców
     *
     * @param messageStatus Status dostarczenia wiadomości
     * @param bulkId        Id wysyłki
     * @return lista odbiorców
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<String> retrieveBulkRecipientsByMessageStatus(MessageStatus messageStatus, Long bulkId) throws JustsendApiClientException;

    /**
     * Anuluje wysyłkę
     *
     * @param bulkId Id wysyłki
     * @return informacje o anulowanej wysyłce
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    BulkResponse cancelBulkById(Long bulkId) throws JustsendApiClientException;

    /**
     * Przyjmuje zgłoszenie masowej wysyłki do. W odpowiedzi zwraca szczegóły wysyłki.
     *
     * @param bulk informacje o wysyłce
     * @return szczegóły wysyłki
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    BulkResponse sendBulk(BulkGroupList bulk) throws JustsendApiClientException;

    /**
     * Przyjmuje zgłoszenie do masowej wysyłki. W odpowiedzi zwraca szczegóły wysyłki.
     *
     * @param bulk informacje o wysyłce
     * @return
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    BulkResponse sendBulk(Bulk bulk) throws JustsendApiClientException;

    /**
     * Przyjmuje zgłoszenie do masowej wysyłki. W odpowiedzi zwraca szczegóły wysyłki.
     *
     * @param bulk informacje o wysyłce
     * @return informacje o wysyłce
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    BulkResponse sendBulkWithoutConfirmation(Bulk bulk) throws JustsendApiClientException;

    /**
     * Zwraca listę dostęmnych nadpisów dla wysyłki FULL
     *
     * @return lista nadpisów
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    List<SenderResponse> retrieveAliases() throws JustsendApiClientException;

    /**
     * Wysyła bulka spersonalizowanego z pliku
     *
     * @param name         Unikalna nazwa wysyłki
     * @param from         Nadawca wiadomości
     * @param data         Data wysyłki (yyyy-MM-ddTHH:mm:ss+02:00)
     * @param bulkVariant  Wariant wysyłki (ECO - basic, FULL - unique, PRO - dynamic, VOICE - voice)
     * @param personalized Czy spersonalizowana wysyłka
     * @param language     Język treści zapisanej w pliku (POLISH - Polski, RUSSIAN - Rosyjski)
     * @param inputData    plik z danymi wysyłki numer telefonu, wiadomość
     * @return bulkId, 0 if input data is empty
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long sendPersonalizedBulk(
            String name,
            String from,
            String data,
            String bulkVariant,
            Boolean personalized,
            LanguageMessage language,
            File inputData) throws JustsendApiClientException;

    /**
     * Wysyła bulka spersonalizowanego z pliku
     *
     * @param name         Unikalna nazwa wysyłki
     * @param from         Nadawca wiadomości
     * @param data         Data wysyłki (yyyy-MM-ddTHH:mm:ss+02:00)
     * @param bulkVariant  Wariant wysyłki (ECO - basic, FULL - unique, PRO - dynamic, VOICE - voice)
     * @param personalized Czy spersonalizowana wysyłka
     * @param inputData    inputData
     * @return bulkId, 0 if input data is empty
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long sendPersonalizedBulk(
            String name,
            String from,
            String data,
            String bulkVariant,
            Boolean personalized,
            File inputData) throws JustsendApiClientException;
}
