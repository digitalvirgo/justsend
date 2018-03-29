package pl.justsend.api.client.services.methods;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 28.03.18
 * Time: 15:38
 */
public enum AccountMethods {

    RETRIEVE_ACCOUNT("/accounts/account/{appKey}"),
    CREATE_SUB_ACCOUNT("/accounts/create/subaccount/{appKey}?appKey={appKey}");

    private String path;

    AccountMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
