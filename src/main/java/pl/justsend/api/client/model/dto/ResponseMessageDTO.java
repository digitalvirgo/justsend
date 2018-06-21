/*
 *  @(#)ResponseMessagesDTO.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class ResponseMessageDTO extends AVBaseDTO<Long> {

    private PrefixDTO prefix;
    private String message;
    private String la;
    private String msisdn;
    private Integer userId;
    private Date createDate;

}