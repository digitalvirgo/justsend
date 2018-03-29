package pl.justsend.api.client.model;

import pl.justsend.api.client.model.enums.JSResponseCode;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 27.03.18
 * Time: 14:46
 */
public class JSResponse {

    private JSResponseCode responseCode;
    private int errorId;
    private String message;
    private String additionalData;


    @Override
    public String toString() {
        return "JSResponse{" + "responseCode=" + responseCode + ", errorId=" + errorId + ", message='" + message + '\''+ ", additionalData='" + additionalData + '\'' + '}';
    }

    public JSResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(JSResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }
}
