package pl.justsend.api.client.services.methods;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 29.03.18
 * Time: 13:29
 */
public enum MessageMethods {

    SEND_MESSAGE("/message/send/{appKey}"),
    SEND_MESSAGE_GET("/message/send/{appKey}/{to}/{from}/{message}/{bulkVariant}"),
    SEND_MESSAGE_ECO("/message/send/{appKey}/{to}/{message}");

    private String path;

    MessageMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
