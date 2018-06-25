/*
 *  @(#)ResponseMessage.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

public class ResponseMessage extends BaseModel{
    private String prefix;
    private String msisdn;
    private String message;
    private String createDate;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(final String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final String createDate) {
        this.createDate = createDate;
    }
}
