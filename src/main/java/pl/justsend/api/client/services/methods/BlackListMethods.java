package pl.justsend.api.client.services.methods;

public enum BlackListMethods {

    ADD_NUMBERS_TO_BLACKLIST("/blacklist/add/numbers/{appKey}"),
    REMOVE_NUMBERS_FROM_BLACKLIST("/blacklist/remove/numbers/{appKey}"),
    RETRIEVE_NUMBERS("/blacklist/{appKey}");

    private String path;

    BlackListMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
