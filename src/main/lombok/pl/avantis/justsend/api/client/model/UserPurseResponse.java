package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPurseResponse extends BaseModel {

    private Long balanceInPoints;
    private Long freePoints;
}
