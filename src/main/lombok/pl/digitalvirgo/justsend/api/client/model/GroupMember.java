package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.MsisdnValidationStatus;

@Data
public class GroupMember {

    private Long id;
    private String msisdn;
    private MsisdnValidationStatus validation;

    public GroupMember(String msisdn) {
        this.msisdn = msisdn;
    }
}