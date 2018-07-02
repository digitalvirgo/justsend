package pl.avantis.justsend.api.client.services.impl.services;

import pl.avantis.justsend.api.client.services.impl.enums.AddressPostBackType;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;

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
