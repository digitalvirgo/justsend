/*
 * Copyright 2017 Digital Virgo S. A. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.pojo;

import pl.digitalvirgo.justsend.api.client.model.AVBase;
import pl.digitalvirgo.justsend.api.client.model.InfobipVoiceLanguage;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Avantis
 * Date: 30.10.2017
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public class PostBackFileDTO extends AVBase<Long> {

    private static final long serialVersionUID = 1L;
    private String name;
    private BulkStatus bulkStatus;
    private Integer importedCount;
    private String deliveryTime;

    /*Voice bulk*/
    private Integer duration;
    private InfobipVoiceLanguage language;

    /*Response bulk*/
    private String sender;
    private String prefix;
    private Long groupId;
    private String groupName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BulkStatus getBulkStatus() {
        return bulkStatus;
    }

    public void setBulkStatus(BulkStatus bulkStatus) {
        this.bulkStatus = bulkStatus;
    }

    public Integer getImportedCount() {
        return importedCount;
    }

    public void setImportedCount(Integer importedCount) {
        this.importedCount = importedCount;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    @Override
    public String toString() {
        return "PostBackFileDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bulkStatus=" + bulkStatus +
                ", importedCount=" + importedCount +
                ", deliveryTime=" + deliveryTime +
                ", duration=" + duration +
                ", language=" + language +
                ", sender='" + sender + '\'' +
                ", prefix='" + prefix + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}