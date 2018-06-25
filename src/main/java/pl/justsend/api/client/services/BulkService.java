package pl.justsend.api.client.services;

import pl.justsend.api.client.model.*;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.File;
import java.util.List;

/**

 * User: posiadacz
 * Date: 04.04.18
 * Time: 14:27
 */
public interface BulkService {

    /**
     *
     * Zwraca informację o masowej wysyłce na podtstawie id wysyłki
     *
     * @param bulkId Id wysyłki
     * @return BulkResponse
     */

    BulkResponse retrieveBulkById(
            Long bulkId) throws JustsendApiClientException;

    /**
     * Zwraca listę odbiorców
     *
     * @param messageStatus Status dostarczenia wiadomości
     * @param bulkId Id wysyłki
     * @return lista odbiorców
     */

    List<String> retrieveBulkRecipientsByMessageStatus(
            MessageStatus messageStatus,
            Long bulkId) throws JustsendApiClientException;

    /**
     * Anuluje wysyłkę
     *
     * @param bulkId Id wysyłki
     * @return
     */

    BulkResponse cancelBulkById(
            Long bulkId) throws JustsendApiClientException;

    /**
     * Przyjmuje zgłoszenie masowej wysyłki do. W odpowiedzi zwraca szczegóły wysyłki.
     *
     * @param bulk informacje o wysyłce
     * @return
     */

    BulkResponse sendBulk(
            BulkGroupList bulk) throws JustsendApiClientException;

    /**
     * Przyjmuje zgłoszenie do masowej wysyłki. W odpowiedzi zwraca szczegóły wysyłki.
     *
     * @param bulk informacje o wysyłce
     * @return
     * @throws JustsendApiClientException
     */

    BulkResponse sendBulk(
            Bulk bulk) throws JustsendApiClientException;

    /**
     * Przyjmuje zgłoszenie do masowej wysyłki. W odpowiedzi zwraca szczegóły wysyłki.
     *
     * @param bulk informacje o wysyłce
     * @return informacje o wysyłce
     */

    BulkResponse sendBulkWithoutConfirmation(
            Bulk bulk) throws JustsendApiClientException;

    /**
     * Zwraca listę dostęmnych nadpisów dla wysyłki FULL
     *
     * @return lista nadpisów
     */

    List<SenderResponse> retrieveAliases() throws JustsendApiClientException;

    /**
     *
     * Wysyła bulka spersonalizowanego z pliku
     *
     * @param name Unikalna nazwa wysyłki
     * @param from Nadawca wiadomości
     * @param data Data wysyłki (yyyy-MM-ddTHH:mm:ss+02:00)
     * @param bulkVariant Wariant wysyłki (ECO - basic, FULL - unique, PRO - dynamic, VOICE - voice)
     * @param personalized Czy spersonalizowana wysyłka
     * @param language Język treści zapisanej w pliku (POLISH - Polski, RUSSIAN - Rosyjski)
     * @param inputData plik z danymi wysyłki numer telefonu, wiadomość
     * @return bulkId, 0 if input data is empty
      */

    Long sendPersonalizedBulk(
            String name,
            String from,
            String data,
            String bulkVariant,
            Boolean personalized,
            LanguageMessage language,
            File inputData) throws JustsendApiClientException;
}
