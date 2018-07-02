/*
 *  @(#)ReportResponse.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.avantis.justsend.api.client.services.impl.enums.BulkStatus;

import java.util.Date;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReportResponse extends BaseModel {

    private Long bulkId;
    private String bulkName;
    private BulkStatus bulkStatus;
    private String from;
    private String message;
    private Long dayPriceInPoints;
    private Long bulkCostInPoints;
    private String estimatedEndDate;
    private Long bulkSize;

    private Long sent;
    private Long delivered;
    private Long notDelivered;
    private Long notSent;
    private Long busy;
    private Long noAnswer;
    private Long undeliverable;

    private Map<String, Long> sentByOperator;
    private Map<String, Long> deliveredByOperator;
    private Map<String, Long> notSentByOperator;
    private Date sendDate;
    private Boolean reportGenerated;
    private Boolean personalizedMessage;
    private LanguageMessage voiceLanguage;
    private Integer voiceDuration;
}