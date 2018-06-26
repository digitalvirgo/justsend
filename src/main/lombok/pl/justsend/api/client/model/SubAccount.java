package pl.justsend.api.client.model;

import lombok.Data;
import pl.justsend.api.client.model.enums.AccountType;
import pl.justsend.api.client.model.enums.UserStatus;

@Data
public class SubAccount {

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
}
