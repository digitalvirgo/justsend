package pl.digitalvirgo.justsend.api.client.services.impl.services;

import pl.digitalvirgo.justsend.api.client.model.Message;
import pl.digitalvirgo.justsend.api.client.model.VoiceMessage;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant;
import pl.digitalvirgo.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

public interface MessageService {

    /**
     * Wysyła pojedynczą wiadomość metodą POST.
     *
     * @param message wysłana wiadomość
     * @return message ID
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long sendMessage(Message message) throws JustsendApiClientException;

    /**
     * Wysyła pojedynczą wiadomość metodą GET. Dla poprawnej obsługi znaków specjalnych wiadomość powinna być zakodowana z wykorzystaniem kodowania UTF-8 i URL encoding.
     *
     * @param to Odbiorca wiadomośc
     * @param from Nadawca wiadomośći
     * @param message Treść wiadomośći
     * @param bulkVariant bulkVariant
     * @return message id
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long sendMessage(String to, String from, String message, BulkVariant bulkVariant) throws JustsendApiClientException;

    /**
     * Wysyła pojedynczą wiadomość ECO. Dla poprawnej obsługi znaków specjalnych wiadomość powinna być zakodowana z wykorzystaniem kodowania UTF-8 i URL encoding.
     *
     * @param to Odbiorca wiadomośc
     * @param message Treść wiadomośći
     * @return message Id
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */

    Long sendMessageECO(String to, String message) throws JustsendApiClientException;

    /**
     * Wysyła wiadomość głosową
     *
     * @param voiceMessage
     * @return message id
     * @throws JustsendApiClientException błąd aplikacji lub niepoprawne zapytanie
     */
    Long sendVoiceMessage(VoiceMessage voiceMessage) throws JustsendApiClientException;
}
