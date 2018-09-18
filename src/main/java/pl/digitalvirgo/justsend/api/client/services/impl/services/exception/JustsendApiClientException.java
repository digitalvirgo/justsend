package pl.digitalvirgo.justsend.api.client.services.impl.services.exception;

import pl.digitalvirgo.justsend.api.client.model.JSResponse;

public class JustsendApiClientException extends RuntimeException {

    private JSResponse jsResponse = new JSResponse();

    public JustsendApiClientException() {
        super();
    }

    public JustsendApiClientException(final String message) {
        super(message);
    }

    public JustsendApiClientException(final String message, JSResponse jsResponse) {
        super(message);
        this.jsResponse = jsResponse;
    }

    public JustsendApiClientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JustsendApiClientException(final Throwable cause) {
        super(cause);
    }

    public JSResponse getJsResponse() {
        return jsResponse;
    }
}