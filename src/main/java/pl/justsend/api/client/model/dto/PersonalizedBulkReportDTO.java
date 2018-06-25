/*
 * Copyright 2017 Digital Virgo S. A. All rights reserved.
 */
package pl.justsend.api.client.model.dto;

import pl.justsend.api.client.model.enums.MessageValidationStatus;
import pl.justsend.api.client.model.enums.MsisdnValidationStatus;

/**

 * User: dbajno
 * Date: 20.10.2017
 * Time: 16:45

 */
public class PersonalizedBulkReportDTO extends AVBaseDTO<Long> {

    private String msisdn;
    private MsisdnValidationStatus msisdnValidation;
    private String messageContent;
    private MessageValidationStatus messageValidation;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public MsisdnValidationStatus getMsisdnValidation() {
        return msisdnValidation;
    }

    public void setMsisdnValidation(MsisdnValidationStatus msisdnValidation) {
        this.msisdnValidation = msisdnValidation;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public MessageValidationStatus getMessageValidation() {
        return messageValidation;
    }

    public void setMessageValidation(MessageValidationStatus messageValidation) {
        this.messageValidation = messageValidation;
    }
}