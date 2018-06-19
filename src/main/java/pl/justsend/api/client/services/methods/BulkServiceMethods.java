package pl.justsend.api.client.services.methods;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 04.04.18
 * Time: 14:27
 */
public enum BulkServiceMethods {

    private String path;

    BulkServiceMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
