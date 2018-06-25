/*
 *  @(#)BulkGroupList.java
 *
 *  Copyright 2015 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import java.util.List;

public class BulkGroupList extends BaseBulk {

    private List<Long> groupIds;

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(final List<Long> groupIds) {
        this.groupIds = groupIds;
    }
}