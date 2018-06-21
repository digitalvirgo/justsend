/*
 * Copyright 2017 Digital Virgo S. A. All rights reserved.
 */
package pl.justsend.api.client.model;

import java.util.List;

/**

 * User: dbajno
 * Date: 24.10.2017
 * Time: 14:39

 */
public class BulkFile extends BaseBulk {

    private List<Long> groupId;

    private Boolean autoSend;

    public List<Long> getGroupId() {
        return groupId;
    }

    public void setGroupId(final List<Long> groupId) {
        this.groupId = groupId != null && groupId.size() > 0 ? groupId : null;
    }

    public Boolean getAutoSend() {
        return autoSend;
    }

    public void setAutoSend(Boolean autoSend) {
        this.autoSend = autoSend;
    }

    @Override
    public String toString() {
        return "BulkFile{" +
                "groupId=" + groupId + '\'' +
                ", name='" + getName() + '\'' +
                ", to=" + getTo() +
                ", from='" + getFrom() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", sendDate='" + getSendDate() + '\'' +
                ", bulkVariant=" + getBulkVariant() +
                ", autoSend=" + getAutoSend() +
                '}';
    }
}