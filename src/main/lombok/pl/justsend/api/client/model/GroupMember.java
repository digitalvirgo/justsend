package pl.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.justsend.api.client.model.enums.MsisdnValidationStatus;

@Data
public class GroupMember {

    private Long id;
    private String number;
    private MsisdnValidationStatus validation;

    public GroupMember(String number) {
        this.number = number;
    }
}