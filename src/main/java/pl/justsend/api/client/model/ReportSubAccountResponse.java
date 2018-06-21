/*
 *  @(#)ReportResponse.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import pl.justsend.api.client.model.enums.BulkStatus;

import java.util.Map;

public class ReportSubAccountResponse extends BaseModel {


    private Long bulkId;
    private String bulkName;
    private BulkStatus bulkStatus;
    private String from;
    private String message;
    private Long dayPriceInPoints;
    private Long bulkCostInPoints;
    private String estimatedEndDate;
    private Long bulkSize;

    private String slaveEmail;

    private Long sent;
    private Long delivered;
    private Long notDelivered;
    private Long notSent;

    private Map<String, Long> sentByOperator;
    private Map<String, Long> deliveredByOperator;
    private Map<String, Long> notSentByOperator;

    public String getSlaveEmail() {
        return slaveEmail;
    }

    public void setSlaveEmail(String slaveEmail) {
        this.slaveEmail = slaveEmail;
    }

    public Long getBulkId() {
        return bulkId;
    }

    public void setBulkId(final Long bulkId) {
        this.bulkId = bulkId;
    }

    public String getBulkName() {
        return bulkName;
    }

    public void setBulkName(final String bulkName) {
        this.bulkName = bulkName;
    }

    public BulkStatus getBulkStatus() {
        return bulkStatus;
    }

    public void setBulkStatus(final BulkStatus bulkStatus) {
        this.bulkStatus = bulkStatus;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Long getDayPriceInPoints() {
        return dayPriceInPoints;
    }

    public void setDayPriceInPoints(final Long dayPriceInPoints) {
        this.dayPriceInPoints = dayPriceInPoints;
    }

    public Long getBulkCostInPoints() {
        return bulkCostInPoints;
    }

    public void setBulkCostInPoints(final Long bulkCostInPoints) {
        this.bulkCostInPoints = bulkCostInPoints;
    }

    public String getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(final String estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public Long getBulkSize() {
        return bulkSize;
    }

    public void setBulkSize(final Long bulkSize) {
        this.bulkSize = bulkSize;
    }

    public Long getSent() {
        return sent;
    }

    public void setSent(final Long sent) {
        this.sent = sent;
    }

    public Long getDelivered() {
        return delivered;
    }

    public void setDelivered(final Long delivered) {
        this.delivered = delivered;
    }

    public Long getNotDelivered() {
        return notDelivered;
    }

    public void setNotDelivered(final Long notDelivered) {
        this.notDelivered = notDelivered;
    }

    public Long getNotSent() {
        return notSent;
    }

    public void setNotSent(final Long notSent) {
        this.notSent = notSent;
    }

    public Map<String, Long> getSentByOperator() {
        return sentByOperator;
    }

    public void setSentByOperator(final Map<String, Long> sentByOperator) {
        this.sentByOperator = sentByOperator;
    }

    public Map<String, Long> getDeliveredByOperator() {
        return deliveredByOperator;
    }

    public void setDeliveredByOperator(final Map<String, Long> deliveredByOperator) {
        this.deliveredByOperator = deliveredByOperator;
    }

    public Map<String, Long> getNotSentByOperator() {
        return notSentByOperator;
    }

    public void setNotSentByOperator(final Map<String, Long> notSentByOperator) {
        this.notSentByOperator = notSentByOperator;
    }
}
