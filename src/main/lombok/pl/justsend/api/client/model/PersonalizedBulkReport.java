/*
 * Copyright 2017 Digital Virgo S. A. All rights reserved.
 */
package pl.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.justsend.api.client.model.enums.MessageValidationStatus;
import pl.justsend.api.client.model.enums.MsisdnValidationStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonalizedBulkReport extends AVBase<Long> {

    private String msisdn;
    private MsisdnValidationStatus msisdnValidation;
    private String messageContent;
    private MessageValidationStatus messageValidation;

}