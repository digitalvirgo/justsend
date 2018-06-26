/*
 *  @(#)ReportSingleBulkResponse.java
 *
 *  Copyright 2016 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReportMessageResponse extends BaseModel {

    private String sendDate;
    private String from;
    private Long delivered;
    private Long notDelivered;
    private Long sent;
    private Long notSent;
    private Long bulkCostInPoints;

}
