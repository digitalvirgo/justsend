package pl.justsend.api.client.services;

import pl.justsend.api.client.model.UserPurseResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

public interface PaymentService {


    Long retrieveCountPoints() throws JustsendApiClientException;

    UserPurseResponse checkBalancePointsUser() throws JustsendApiClientException;

    String setBalancePointsUser(Integer subId, Long points) throws JustsendApiClientException;
}
