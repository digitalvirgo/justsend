package pl.justsend.api.client.services;

import pl.justsend.api.client.model.enums.AddressPostBackType;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

public interface PostBackService {

    /**
     * Wysyła testowo na serwer podanego jsona jako test post back
     *
     * @param address Oznacza adres na jaki zostanie wysłana wiadomość
     * @param json    Json jako test post back
     * @return test: OK
     */

    String sendPostBack(AddressPostBackType address, String json) throws JustsendApiClientException;
}
