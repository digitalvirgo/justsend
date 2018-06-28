package pl.justsend.api.client.model;

import lombok.Data;
import pl.justsend.api.client.model.enums.AccountType;
import pl.justsend.api.client.model.enums.MsisdnValidationStatus;
import pl.justsend.api.client.model.enums.UserStatus;
import pl.justsend.api.client.model.enums.UserType;

import java.util.Date;

@Data
public class Account {

    private Long id;
    private String email;
    private String password;
    private String appKey;
    private String verifyCode;
    private String paymentCode;
    private Integer channelId;

    private String firstname;
    private String surname;
    private String pesel;
    private String idNo;
    private String nip;

    private String companyName;
    private String krs;
    private String regon;

    private String contactPerson;
    private String registerEntry;
    private String description;

    private String street;
    private String houseNo;
    private String flatNo;
    private String postcode;
    private String city;

    private String contactFirstname;
    private String contactSurname;
    private String contactPhone;
    private MsisdnValidationStatus msisdnValidationStatus;
    private Date registerDate;

    private UserStatus userStatus;
    private UserType userType;
    private String department;

    private AccountType accountType;
    private Integer masterId;

    private Integer administrator;
    private Integer variantId;
}