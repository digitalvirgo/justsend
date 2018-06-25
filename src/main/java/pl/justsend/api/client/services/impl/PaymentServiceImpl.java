package pl.justsend.api.client.services.impl;

import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.model.UserPurseResponse;
import pl.justsend.api.client.services.BaseService;
import pl.justsend.api.client.services.PaymentService;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;

import static java.lang.String.valueOf;

public class PaymentServiceImpl extends BaseService implements PaymentService {

    /**
     *
     * @param appKey Klucz api
     */

    public PaymentServiceImpl(String appKey) {
        super(appKey);
    }

    @Override
    public Long retrieveCountPoints() throws JustsendApiClientException {
        String url = createURL("/payment/points/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Long.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public UserPurseResponse checkBalancePointsUser() throws JustsendApiClientException {
        String url = createURL("/payment/points/subaccount/{appKey}");

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, UserPurseResponse.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }

    @Override
    public String setBalancePointsUser(final Integer subId, final Long points) throws JustsendApiClientException {
        String url = createURL("/payment/subaccount/{appKey}/{subId}/{points}","subId", valueOf(subId), "points", valueOf(points));

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, null);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
    }
}
