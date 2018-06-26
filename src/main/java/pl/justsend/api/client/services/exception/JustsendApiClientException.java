package pl.justsend.api.client.services.exception;

import pl.justsend.api.client.model.JSResponse;

public class JustsendApiClientException extends Exception {

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