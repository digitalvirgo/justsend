/*
 * Copyright 2017 Digital Virgo S. A. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.pojo;

import pl.digitalvirgo.justsend.api.client.model.AVBase;
import pl.digitalvirgo.justsend.api.client.model.InfobipVoiceLanguage;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Avantis
 * Date: 30.10.2017
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public class PostBackRecipientDTO extends AVBase<Long> {

    private static final long serialVersionUID = 1L;
    private String msisdn;
    private String operator;
    private JSAcknowledgeStatus deliveryStatus;
    private Date deliveryTime;
    private Long ackId;

    /*Voice recipient*/
    private Integer duration;
    private InfobipVoiceLanguage language;

    /*Response recipient*/
    private String sender;
    private String prefix;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public JSAcknowledgeStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(JSAcknowledgeStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getAckId() {
        return ackId;
    }

    public void setAckId(Long ackId) {
        this.ackId = ackId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public InfobipVoiceLanguage getLanguage() {
        return language;
    }

    public void setLanguage(InfobipVoiceLanguage language) {
        this.language = language;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "PostBackRecipientDTO{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", operator='" + operator + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                ", deliveryTime=" + deliveryTime +
                ", ackId=" + ackId +
                ", duration=" + duration +
                ", language=" + language +
                ", sender='" + sender + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}