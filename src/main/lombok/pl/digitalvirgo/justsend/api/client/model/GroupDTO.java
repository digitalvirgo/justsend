package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupDTO extends AVBase<Long> {

    private String name;
    private List<GroupMember> members = new ArrayList<>();
    private Integer userId;
    private Date createDate;
    private Date dateFrom;
    private Date dateTo;
    private String smsOutContent;
    private Boolean fileImporting;
    private String shortName;
}