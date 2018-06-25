package pl.justsend.api.client.services.impl;

import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.model.enums.AddressPostBackType;
import pl.justsend.api.client.services.BaseService;
import pl.justsend.api.client.services.PostBackService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;

public class PostBackServiceImpl extends BaseService implements PostBackService {

    /**
     *
     * @param appKey Klucz api
     */

    public PostBackServiceImpl(String appKey) {
        super(appKey);
    }

    /**
     * Wysyła testowo na serwer podanego jsona jako test post back
     *
     * @param address Oznacza adres na jaki zostanie wysłana wiadomość
     * @param json Json jako test post back
     * @return test: OK
     */

    @Override
    public String sendPostBack(final AddressPostBackType address, final String json) throws JustsendApiClientException {
        String url = createURL("/postBack/sendPostBack/{appKey}/{address}", "address", address.name());

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, json);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
