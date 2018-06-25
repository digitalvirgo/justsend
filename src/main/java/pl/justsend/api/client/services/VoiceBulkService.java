package pl.justsend.api.client.services;

/**

 * User: posiadacz
 * Date: 04.04.18
 * Time: 14:58
 */
public interface VoiceBulkService {

    void sendPersonalizedBulk();

    void send();

    void sendToGroups();

    void sendWithourConfirmation();

}
