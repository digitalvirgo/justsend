package pl.justsend.api.client.services;

import pl.justsend.api.client.model.Message;
import pl.justsend.api.client.model.enums.BulkVariant;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

/**

 * User: posiadacz
 * Date: 29.03.18
 * Time: 12:33
 */
public interface MessageService {

    /**
     * Wysyła pojedynczą wiadomość metodą POST.
     *
     * @param message wysłana wiadomość
     * @return message ID
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
     */

    Long sendMessage(String to, String from, String message, BulkVariant bulkVariant) throws JustsendApiClientException;

    /**
     * Wysyła pojedynczą wiadomość ECO. Dla poprawnej obsługi znaków specjalnych wiadomość powinna być zakodowana z wykorzystaniem kodowania UTF-8 i URL encoding.
     *
     * @param to Odbiorca wiadomośc
     * @param message Treść wiadomośći
     * @return message Id
     */

    Long sendMessageECO(String to, String message) throws JustsendApiClientException;

//    void sendVoiceMessage(String to, String from, String message, BulkVariant bulkVariant);
}
