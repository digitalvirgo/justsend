package pl.avantis.justsend.api.client.services.impl;

import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.services.impl.enums.AddressPostBackType;
import pl.avantis.justsend.api.client.services.impl.services.PostBackService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

import java.io.IOException;

public class PostBackServiceImpl extends BaseService implements PostBackService {

    /**
     * @param appKey Klucz api
     */

    public PostBackServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public String sendPostBack(final AddressPostBackType address, final String json) throws JustsendApiClientException {
        String url = createURL("/v2/postBack/sendPostBack/{address}", "address", address.name());

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, json);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
