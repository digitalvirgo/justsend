/*
 *  @(#)Group.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import java.util.List;

public class Group extends BaseModel{
    private long groupId;
    private String name;
    private List<String> members;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(final long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(final List<String> members) {
        this.members = members;
    }
}
