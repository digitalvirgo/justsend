/*
 *  @(#)Group.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class GroupResponse extends Group {

    @JsonIgnore
    @Override
    public List<String> getMembers() {
        return super.getMembers();
    }
}
