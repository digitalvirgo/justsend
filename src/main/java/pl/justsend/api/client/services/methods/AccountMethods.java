package pl.justsend.api.client.services.methods;

public enum AccountMethods {

    RETRIEVE_ACCOUNT("/accounts/account/{appKey}"),
    CREATE_SUB_ACCOUNT("/accounts/create/subaccount/{appKey}?appKey={appKey}"),
    DEACTIVATE_ACCOUNT("/accounts/deactivate/{userAppKey}"),
    EDIT_SUB_ACCOUNT("/accounts/edit/subaccount/{appKey}/{subId}"),
    RESET_SUB_ACCOUNT("/accounts/reset/{appKey}/{subId}"),
    RETRIEVE_SUB_ACCOUNT("/accounts/subaccount/{appKey}"),
    RETRIEVE_SUB_ACCOUNTS_LIST("/accounts/subaccounts/list/{appKey}");

    private String path;

    AccountMethods(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
