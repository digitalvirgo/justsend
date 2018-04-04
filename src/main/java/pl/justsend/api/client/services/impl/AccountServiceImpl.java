package pl.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.justsend.api.client.http.utils.JSONSerializer;
import pl.justsend.api.client.model.Account;
import pl.justsend.api.client.model.JSResponse;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.model.SubAccountRaw;
import pl.justsend.api.client.services.AccountService;
import pl.justsend.api.client.services.BaseService;
import pl.justsend.api.client.services.methods.AccountMethods;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 21.03.18
 * Time: 16:03
 */
public class AccountServiceImpl extends BaseService implements AccountService {

    public AccountServiceImpl(String appKey) {
        super(appKey);
    }

    public Account retrieveAccount() throws JustsendApiClientException {

        String url = createURL(AccountMethods.RETRIEVE_ACCOUNT.getPath());

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, Account.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public SubAccount createSubAccount(SubAccountRaw subAccountRaw) throws JustsendApiClientException {

        String json = JSONSerializer.serialize(subAccountRaw);
        String url = createURL(AccountMethods.CREATE_SUB_ACCOUNT.getPath());

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, json);
            return processResponse(jsResponse, SubAccount.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }
        
    }

    @Override
    public String deactivateAccount(String userAppKey) throws JustsendApiClientException {

        String url = createURL(AccountMethods.DEACTIVATE_ACCOUNT.getPath(), "appKey", userAppKey);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public SubAccount editSubAccount(String userAppKey, String firstName, String surname, String password, String description) throws JustsendApiClientException {

        String url = createURL(AccountMethods.EDIT_SUB_ACCOUNT.getPath(), "subId", userAppKey, "firstName", firstName, "surname", surname, "password", password, "description", description);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, SubAccount.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public String resetSubAccount(String userAppKey) throws JustsendApiClientException {

        String url = createURL(AccountMethods.RESET_SUB_ACCOUNT.getPath(), "subId", userAppKey);

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, null);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public SubAccount retrieveSubAccount(String userAppKey) throws JustsendApiClientException {

        String url = createURL(AccountMethods.RETRIEVE_SUB_ACCOUNT.getPath(), "appKey", userAppKey);

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, SubAccount.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public List<SubAccount> retrieveSubAccountsList() throws JustsendApiClientException {

        String url = createURL(AccountMethods.RETRIEVE_SUB_ACCOUNTS_LIST.getPath());

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url);
            return processResponse(jsResponse, new TypeToken<List<SubAccount>>(){}.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }
}
