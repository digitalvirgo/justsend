/*
 *  @(#)Bulk.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

public class Bulk extends BaseBulk {

    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(final Long groupId) {
        this.groupId = groupId != null && groupId > 0 ? groupId : null;
    }

    @Override
    public String toString() {
        return "Bulk{" +
                "groupId=" + groupId + '\'' +
                ", name='" + getName() + '\'' +
                ", to=" + getTo() +
                ", from='" + getFrom() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", sendDate='" + getSendDate() + '\'' +
                ", bulkVariant=" + getBulkVariant() + '\'' +
                ", personalizedMessage=" + isPersonalizedMessage() +
                '}';
    }
}