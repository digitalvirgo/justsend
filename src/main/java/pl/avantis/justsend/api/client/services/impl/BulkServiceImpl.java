package pl.avantis.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.avantis.justsend.api.client.model.Bulk;
import pl.avantis.justsend.api.client.model.BulkGroupList;
import pl.avantis.justsend.api.client.model.BulkResponse;
import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.model.LanguageMessage;
import pl.avantis.justsend.api.client.model.MessageStatus;
import pl.avantis.justsend.api.client.model.PersonalizedBulkReport;
import pl.avantis.justsend.api.client.model.SenderResponse;
import pl.avantis.justsend.api.client.services.impl.services.BulkService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

public class BulkServiceImpl extends BaseService implements BulkService {

    /**
     * @param appKey Klucz api
     */

    public BulkServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public BulkResponse retrieveBulkById(
            final Long bulkId) throws JustsendApiClientException {
        String url = createURL("/v2/bulk/{bulkId}", "bulkId", valueOf(bulkId));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, BulkResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<String> retrieveBulkRecipientsByMessageStatus(
            final MessageStatus messageStatus,
            final Long bulkId) throws JustsendApiClientException {
        String url = createURL("/v2/bulk/recipient/{messageStatus}/{bulkId}", "messageStatus", messageStatus.name(), "bulkId", valueOf(bulkId));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<String>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public BulkResponse cancelBulkById(
            final Long bulkId) throws JustsendApiClientException {
        String url = createURL("/v2/bulk/cancel/{bulkId}", "bulkId", valueOf(bulkId));

        try {

            JSResponse jsResponse = justsendHttpClient.doPut(url);
            return processResponse(jsResponse, BulkResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public BulkResponse sendBulk(
            final BulkGroupList bulk) throws JustsendApiClientException {
        String url = createURL("/v2/bulk/sendToGroups");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, bulk);
            return processResponse(jsResponse, BulkResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public BulkResponse sendBulk(
            final Bulk bulk) throws JustsendApiClientException {
        String url = createURL("/v2/bulk/send");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, bulk);
            return processResponse(jsResponse, BulkResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public BulkResponse sendBulkWithoutConfirmation(
            final Bulk bulk) throws JustsendApiClientException {
        String url = createURL("/v2/bulk/sendWithoutConfirmation");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, bulk);
            return processResponse(jsResponse, BulkResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<SenderResponse> retrieveAliases() throws JustsendApiClientException {
        String url = createURL("/v2/bulk/sender/list");

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SenderResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    public List<PersonalizedBulkReport> retrieveBulkRecipientsByPersonalizedMessageStatus(
            final MessageStatus messageStatus,
            final Long bulkId) throws JustsendApiClientException {

        String url = createURL("/v2/bulk/recipient/personalized/{messageStatus}/{bulkId}"
                , "messageStatus", messageStatus.name(), "bulkId", valueOf(bulkId));

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SenderResponse>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Long sendPersonalizedBulk(
            final String name,
            final String from,
            final String data,
            final String bulkVariant,
            final Boolean personalized,
            final File inputData) throws JustsendApiClientException {

        String url = createURL("/v2/bulk/send/personalized/{name}/{from}/{data}/{bulkVariant}/{personalized}",
                "name", name, "from", from, "data", data, "bulkVariant", bulkVariant,
                "personalized", personalized.toString());

        try {

            JSResponse jsResponse = justsendHttpClient.doMultiPartPost(url, "inputData", inputData, null,null);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public Long sendPersonalizedBulk(
            final String name,
            final String from,
            final String data,
            final String bulkVariant,
            final Boolean personalized,
            final LanguageMessage language,
            final File inputData) throws JustsendApiClientException {

        String url = createURL("/v2/bulk/send/personalized/{name}/{from}/{data}/{bulkVariant}/{personalized}/{language}",
                "name", name, "from", from, "data", data, "bulkVariant", bulkVariant,
                "personalized", personalized.toString(), "language", language.name());

        try {

            JSResponse jsResponse = justsendHttpClient.doMultiPartPost(url, "inputData", inputData, null,null);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
