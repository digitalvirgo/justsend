package pl.justsend.api.client.services;

import pl.justsend.api.client.model.enums.AddressPostBackType;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

public interface PostBackService {
    String sendPostBack(
            AddressPostBackType address,
            String json) throws JustsendApiClientException;
}
