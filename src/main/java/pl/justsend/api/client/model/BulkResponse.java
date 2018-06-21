/*
 *  @(#)BulkResponse.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.justsend.api.client.model.enums.BulkStatus;

import java.util.List;

public class BulkResponse extends BulkGroupList {

    private Long id;
    private String estimatedEndDate;
    private Integer costInPoints;
    private String shortName;
    private BulkStatus bulkStatus;
    private Integer bulkSize;
    private Boolean autoSend;
    private Integer voiceDuration;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(final String estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public Integer getCostInPoints() {
        return costInPoints;
    }

    public void setCostInPoints(Integer costInPoints) {
        this.costInPoints = costInPoints;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public BulkStatus getBulkStatus() {
        return bulkStatus;
    }

    public void setBulkStatus(final BulkStatus bulkStatus) {
        this.bulkStatus = bulkStatus;
    }

    public Integer getBulkSize() {
        return bulkSize;
    }

    public void setBulkSize(final Integer bulkSize) {
        this.bulkSize = bulkSize;
    }

    public Boolean getAutoSend() {
        return autoSend;
    }

    public void setAutoSend(Boolean autoSend) {
        this.autoSend = autoSend;
    }

    public Integer getVoiceDuration() {
        return voiceDuration;
    }

    public void setVoiceDuration(Integer voiceDuration) {
        this.voiceDuration = voiceDuration;
    }

    @JsonIgnore
    @Override
    public List<String> getTo() {
        return null;
    }
}
