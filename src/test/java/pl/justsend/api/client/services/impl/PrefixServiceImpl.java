package pl.justsend.api.client.services.impl;

import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.model.Prefix;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

class PrefixServiceImpl extends BaseService {

    PrefixServiceImpl(String appKey) {
        super(appKey);
    }

    Prefix createPrefix(final Prefix prefix) throws JustsendApiClientException {
        String url = createURL("/prefix/createPrefix/{appKey}");

        try {
            JSResponse jsResponse = justsendHttpClient.doPost(url, prefix);
            return processResponse(jsResponse, Prefix.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    List<Prefix> retrieveAllPrefixesPagin(final String type, final Integer rowFrom, final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/prefix/retrieveAllPrefixesPagin/{appKey}/{type}/{rowFrom}/{rowSize}",
                "type", type, "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));

        try {
            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Prefix.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

}
