package pl.digitalvirgo.justsend.api.client.test.helpers.builder;

import pl.digitalvirgo.justsend.api.client.model.GroupCreate;
import pl.digitalvirgo.justsend.api.client.model.GroupMember;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

public final class GroupCreateBuilder {
    private String name;
    private List<GroupMember> members;
    private Date dateFrom;
    private Date dateTo;
    private String groupSmsContent;

    private GroupCreateBuilder() {
    }

    public static GroupCreateBuilder aGroupCreateWithDefaults() {
        GroupCreateBuilder groupCreate = new GroupCreateBuilder();
        groupCreate.withName("Ż\"nameść:/");
        groupCreate.withMembers(asList(new GroupMember("514678987"), new GroupMember("514678988")));
        return groupCreate;
    }

    public static GroupCreateBuilder aGroupCreate() {
        return new GroupCreateBuilder();
    }

    public GroupCreateBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GroupCreateBuilder withMembers(List<GroupMember> members) {
        this.members = members;
        return this;
    }

    public GroupCreateBuilder withDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public GroupCreateBuilder withDateTo(Date dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public GroupCreateBuilder withGroupSmsContent(String groupSmsContent) {
        this.groupSmsContent = groupSmsContent;
        return this;
    }

    public GroupCreate build() {
        GroupCreate groupCreate = new GroupCreate();
        groupCreate.setName(name);
        groupCreate.setMembers(members);
        groupCreate.setDateFrom(dateFrom);
        groupCreate.setDateTo(dateTo);
        groupCreate.setGroupSmsContent(groupSmsContent);
        return groupCreate;
    }
}
