package pl.justsend.api.client.services;

import pl.justsend.api.client.model.Account;
import pl.justsend.api.client.model.SubAccount;
import pl.justsend.api.client.model.SubAccountRaw;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 21.03.18
 * Time: 15:43
 */
public interface AccountService {

    Account retrieveAccount() throws JustsendApiClientException;

    SubAccount createSubAccount(SubAccountRaw subAccountRaw) throws JustsendApiClientException;

}
