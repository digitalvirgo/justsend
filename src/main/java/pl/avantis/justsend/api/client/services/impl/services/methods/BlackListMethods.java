package pl.avantis.justsend.api.client.services.impl.services.methods;

public enum BlackListMethods {

    ADD_NUMBERS_TO_BLACKLIST("/v2/blacklist/add/numbers"),
    REMOVE_NUMBERS_FROM_BLACKLIST("/v2/blacklist/remove/numbers"),
    RETRIEVE_NUMBERS("/v2/blacklist");

    private String path;

    BlackListMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
