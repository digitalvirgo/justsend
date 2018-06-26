package pl.justsend.api.client.services.methods;

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
