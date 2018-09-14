/*
 *  @(#)ReportResponse.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkStatus;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReportSubAccountResponse extends BaseModel {

    private Long bulkId;
    private String bulkName;
    private BulkStatus bulkStatus;
    private String from;
    private String message;
    private Long dayPriceInPoints;
    private Long bulkCostInPoints;
    private String estimatedEndDate;
    private Long bulkSize;

    private String slaveEmail;

    private Long sent;
    private Long delivered;
    private Long notDelivered;
    private Long notSent;

    private Map<String, Long> sentByOperator;
    private Map<String, Long> deliveredByOperator;
    private Map<String, Long> notSentByOperator;

}
