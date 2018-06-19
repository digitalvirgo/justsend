package pl.justsend.api.client.services.methods;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 04.04.18
 * Time: 15:06
 */
public enum VoiceBulkServiceMethods {

    private String path;

    VoiceBulkServiceMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
