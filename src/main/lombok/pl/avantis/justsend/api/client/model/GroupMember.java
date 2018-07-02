package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.avantis.justsend.api.client.services.impl.enums.MsisdnValidationStatus;

@Data
public class GroupMember {

    private Long id;
    private String number;
    private MsisdnValidationStatus validation;

    public GroupMember(String number) {
        this.number = number;
    }
}