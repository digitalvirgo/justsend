package pl.avantis.justsend.api.client.services.impl;

import com.google.gson.reflect.TypeToken;
import pl.avantis.justsend.api.client.model.Account;
import pl.avantis.justsend.api.client.model.JSResponse;
import pl.avantis.justsend.api.client.model.SubAccount;
import pl.avantis.justsend.api.client.model.SubAccountRaw;
import pl.avantis.justsend.api.client.services.impl.http.utils.JSONSerializer;
import pl.avantis.justsend.api.client.services.impl.services.AccountService;
import pl.avantis.justsend.api.client.services.impl.services.exception.JustsendApiClientException;
import pl.avantis.justsend.api.client.services.impl.services.methods.AccountMethods;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static java.lang.String.valueOf;

public class AccountServiceImpl extends BaseService implements AccountService {

    /**
     * @param appKey Klucz api
     */

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

        String url = createURL(AccountMethods.DEACTIVATE_ACCOUNT.getPath());

        try {

            JSResponse jsResponse = justsendHttpClient.doGet(url, userAppKey);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public SubAccount editSubAccount(Long subUserID, String firstName, String surname, String password, String description) throws JustsendApiClientException {

        try {
            String url = createURL(AccountMethods.EDIT_SUB_ACCOUNT.getPath(), "subId", valueOf(subUserID));

            url = addParameters(url, "firstname", firstName, "surname", surname, "password", password, "description", description);

            JSResponse jsResponse = justsendHttpClient.doPost(url, null);
            return processResponse(jsResponse, SubAccount.class);

        } catch (UnsupportedEncodingException e) {
            throw new JustsendApiClientException("problem encoding password : " + e.getMessage());
        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public String resetSubAccount(Long subId) throws JustsendApiClientException {

        String url = createURL(AccountMethods.RESET_SUB_ACCOUNT.getPath(), "subId", valueOf(subId));

        try {

            JSResponse jsResponse = justsendHttpClient.doPost(url, null);
            return processResponse(jsResponse, String.class);

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }

    @Override
    public SubAccount retrieveSubAccount(String userAppKey) throws JustsendApiClientException {

        String url = createURL(AccountMethods.RETRIEVE_SUB_ACCOUNT.getPath());

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
            return processResponse(jsResponse, new TypeToken<List<SubAccount>>() {
            }.getType());

        } catch (IOException e) {
            throw new JustsendApiClientException("connection failed: " + e.getMessage());
        }

    }
}
