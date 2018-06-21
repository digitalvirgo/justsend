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

    Long sendMessage(Message message) throws JustsendApiClientException;

    Long sendMessage(String to, String from, String message, BulkVariant bulkVariant) throws JustsendApiClientException;

    Long sendMessageECO(String to, String message) throws JustsendApiClientException;

//    void sendVoiceMessage(String to, String from, String message, BulkVariant bulkVariant);
}
