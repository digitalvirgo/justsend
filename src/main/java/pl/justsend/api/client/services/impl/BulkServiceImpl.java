package pl.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.justsend.api.client.model.*;
import pl.justsend.api.client.services.BulkService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

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
        String url = createURL("/bulk/{appKey}/{bulkId}", "bulkId", valueOf(bulkId));

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
        String url = createURL("/bulk/recipient/{appKey}/{messageStatus}/{bulkId}", "messageStatus", messageStatus.name(), "bulkId", valueOf(bulkId));

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
        String url = createURL("/bulk/cancel/{appKey}/{bulkId}", "bulkId", valueOf(bulkId));

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
        String url = createURL("/bulk/sendToGroups/{appKey}");

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
        String url = createURL("/bulk/send/{appKey}");

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
        String url = createURL("/bulk/sendWithoutConfirmation/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, bulk);
            return processResponse(jsResponse, BulkResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<SenderResponse> retrieveAliases() throws JustsendApiClientException {
        String url = createURL("/bulk/sender/list/{appKey}");

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

        String url = createURL("/bulk/recipient/personalized/{appKey}/{messageStatus}/{bulkId}"
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
            final LanguageMessage language,
            final File inputData) throws JustsendApiClientException {

        String url = createURL("/bulk/send/personalized/{appKey}/{name}/{from}/{data}/{bulkVariant}/{personalized}/{language}",
                "name", name, "from", from, "data", data, "bulkVariant", bulkVariant,
                "personalized", personalized.toString(), "language", language.name());

        try {

            JSResponse jsResponse = justsendHttpClient.doMultiPartPost(url, inputData);
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
            final File inputData) throws JustsendApiClientException {

        String url = createURL("/bulk/send/personalized/{appKey}/{name}/{from}/{data}/{bulkVariant}/{personalized}",
                "name", name, "from", from, "data", data, "bulkVariant", bulkVariant,
                "personalized", personalized.toString());

        try {

            JSResponse jsResponse = justsendHttpClient.doMultiPartPost(url, inputData);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
