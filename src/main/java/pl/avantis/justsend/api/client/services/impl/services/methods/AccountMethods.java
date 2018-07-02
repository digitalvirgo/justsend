package pl.avantis.justsend.api.client.services.impl.services.methods;

public enum AccountMethods {

    RETRIEVE_ACCOUNT("/v2/accounts/account"),
    CREATE_SUB_ACCOUNT("/v2/accounts/create/subaccount"),
    DEACTIVATE_ACCOUNT("/v2/accounts/deactivate"),
    EDIT_SUB_ACCOUNT("/v2/accounts/edit/subaccount/{subId}"),
    RESET_SUB_ACCOUNT("/v2/accounts/reset/{subId}"),
    RETRIEVE_SUB_ACCOUNT("/v2/accounts/subaccount"),
    RETRIEVE_SUB_ACCOUNTS_LIST("/v2/accounts/subaccounts/list");

    private String path;

    AccountMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
