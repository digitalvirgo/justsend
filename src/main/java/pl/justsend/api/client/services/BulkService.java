package pl.justsend.api.client.services;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 04.04.18
 * Time: 14:27
 */
public interface BulkService {

    void cancelBulkById();

    void retrievePersonalizedBulkRecipientsByMessageStatus();

    void retrieveBulkRecipientsByMessageStatus();

    void retrieveSenderAliases();

    void retrieveBulkById();

}
