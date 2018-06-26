/*
 *  @(#)ReportResponseMessage.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReportResponseMessage extends BaseModel {

    private String prefix;
    private String number;
    private String message;
    private String createDate;
}
