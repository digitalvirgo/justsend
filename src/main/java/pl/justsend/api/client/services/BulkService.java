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

    BulkResponse retrieveBulkById(
            Long bulkId) throws JustsendApiClientException;

    List<String> retrieveBulkRecipientsByMessageStatus(
            MessageStatus messageStatus,
            Long bulkId) throws JustsendApiClientException;

    BulkResponse cancelBulkById(
            Long bulkId) throws JustsendApiClientException;

    BulkResponse sendBulk(
            BulkGroupList bulk) throws JustsendApiClientException;

    BulkResponse sendBulk(
            Bulk bulk) throws JustsendApiClientException;

    BulkResponse sendBulkWithoutConfirmation(
            Bulk bulk) throws JustsendApiClientException;

    List<SenderResponse> retrieveAliases() throws JustsendApiClientException;

    Long sendPersonalizedBulk(
            String name,
            String from,
            String data,
            String bulkVariant,
            Boolean personalized,
            LanguageMessage language,
            File inputData) throws JustsendApiClientException;
}
