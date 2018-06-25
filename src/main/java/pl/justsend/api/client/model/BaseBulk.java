/*
 *  @(#)BaseBulk.java
 *
 *  Copyright 2015 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import pl.justsend.api.client.model.enums.BulkVariant;

import java.util.List;

public abstract class BaseBulk extends BaseModel {

    private String name;

    private List<String> to;

    private String from;

    private String message;

    private String sendDate;

    private BulkVariant bulkVariant;

    private boolean personalizedMessage = false;

    private LanguageMessage language;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(final List<String> to) {
        this.to = to;
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

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(final String sendDate) {
        this.sendDate = sendDate;
    }

    public BulkVariant getBulkVariant() {
        return bulkVariant;
    }

    public void setBulkVariant(final BulkVariant bulkVariant) {
        this.bulkVariant = bulkVariant;
    }

    public boolean isPersonalizedMessage() {
        return personalizedMessage;
    }

    public void setPersonalizedMessage(boolean personalizedMessage) {
        this.personalizedMessage = personalizedMessage;
    }

    public LanguageMessage getLanguage() {
        return language;
    }

    public void setLanguage(LanguageMessage language) {
        this.language = language;
    }
}