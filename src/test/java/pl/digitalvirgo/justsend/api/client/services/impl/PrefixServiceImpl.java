package pl.digitalvirgo.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.digitalvirgo.justsend.api.client.model.JSResponse;
import pl.digitalvirgo.justsend.api.client.model.Prefix;
import pl.digitalvirgo.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.util.List;

import static java.lang.String.valueOf;

class PrefixServiceImpl extends BaseService {

    PrefixServiceImpl(String appKey) {
        super(appKey);
    }

    Prefix createPrefix(final Prefix prefix) throws JustsendApiClientException {
        String url = createURL("/v2/prefix/createPrefix");

        try {
            JSResponse jsResponse = justsendHttpClient.doPost(url, prefix);
            return processResponse(jsResponse, Prefix.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    List<Prefix> retrieveAllPrefixesPagin(final String type /*GLOBAL, DEDICATED*/, final Integer rowFrom, final Integer rowSize) throws JustsendApiClientException {
        String url = createURL("/v2/prefix/retrieveAllPrefixesPagin/{type}/{rowFrom}/{rowSize}",
                "type", type, "rowFrom", valueOf(rowFrom), "rowSize", valueOf(rowSize));

        try {
            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<Prefix>>(){}.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
