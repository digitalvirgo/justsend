/*
 *  @(#)GroupCreate.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class GroupCreate extends Group {

    @JsonIgnore
    @Override
    public long getGroupId() {
        return super.getGroupId();
    }
}