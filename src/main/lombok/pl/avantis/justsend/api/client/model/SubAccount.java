package pl.avantis.justsend.api.client.model;

import lombok.Data;
import pl.avantis.justsend.api.client.services.impl.enums.AccountType;
import pl.avantis.justsend.api.client.services.impl.enums.UserStatus;

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
