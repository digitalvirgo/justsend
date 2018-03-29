package pl.justsend.api.client.services.exception;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 28.03.18
 * Time: 11:36
 */
public class JustsendApiClientException extends Exception {

    public JustsendApiClientException() {
        super();
    }

    public JustsendApiClientException(final String message) {
        super(message);
    }

    public JustsendApiClientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JustsendApiClientException(final Throwable cause) {
        super(cause);
    }

}