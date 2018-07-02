package pl.avantis.justsend.api.client.services.impl.services.methods;

public enum MessageMethods {

    SEND_MESSAGE("/v2/message/send"),
    SEND_MESSAGE_GET("/v2/message/send/{to}/{from}/{message}/{bulkVariant}"),
    SEND_MESSAGE_ECO("/v2/message/send/{to}/{message}");

    private String path;

    MessageMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
