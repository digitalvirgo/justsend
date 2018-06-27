/*
 *  @(#)ResponseMessagesDTO.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class PanelReportResponseMessage extends AVBase<Long> {

    private Prefix prefix;
    private String message;
    private String la;
    private String msisdn;
    private Integer userId;
    private Date createDate;

}