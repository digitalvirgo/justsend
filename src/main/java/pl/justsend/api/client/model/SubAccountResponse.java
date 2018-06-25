package pl.justsend.api.client.model;

import pl.justsend.api.client.model.enums.AccountType;
import pl.justsend.api.client.model.enums.UserStatus;

/**

 * User: lciesluk
 * Date: 08.10.14
 * Time: 15:48

 */
public class SubAccountResponse extends BaseModel {

    private Long subid;
    private String email;
    private String password;
    private String appKey;
    private String description;
    private String contactFirstname;
    private String contactSurname;
    private AccountType accountType;
    private UserStatus userStatus;
    private Integer masterId;

    public Long getSubid() {
        return subid;
    }

    public void setSubid(Long subid) {
        this.subid = subid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactFirstname() {
        return contactFirstname;
    }

    public void setContactFirstname(String contactFirstname) {
        this.contactFirstname = contactFirstname;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public void setContactSurname(String contactSurname) {
        this.contactSurname = contactSurname;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
