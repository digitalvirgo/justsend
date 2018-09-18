/*
 * Copyright 2018 Digital Virgo S. A. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.pojo;

import pl.digitalvirgo.justsend.api.client.model.AVBase;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jzaczek
 * Date: 06.07.2018
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class PostBackResponseDTO extends AVBase<Long> {

    private static final long serialVersionUID = 1L;
    private String msisdn;
    private String operator;
    private Date deliveryTime;
    private String sender;
    private String prefix;
    private Long groupId;
    private String groupName;
    private Long idMsgContent;

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

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getIdMsgContent() {
        return idMsgContent;
    }

    public void setIdMsgContent(Long idMsgContent) {
        this.idMsgContent = idMsgContent;
    }

    @Override
    public String toString() {
        return "PostBackResponseDTO{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", operator='" + operator + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", sender='" + sender + '\'' +
                ", prefix='" + prefix + '\'' +
                ", groupId=" + groupId +
                ", groupNAme='" + groupName + '\'' +
                ", idMsgContent=" + idMsgContent +
                '}';
    }
}