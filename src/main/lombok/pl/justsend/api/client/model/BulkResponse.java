/*
 *  @(#)BulkResponse.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.justsend.api.client.model.enums.BulkStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BulkResponse extends BulkGroupList {

    private Long id;
    private String estimatedEndDate;
    private Integer costInPoints;
    private String shortName;
    private BulkStatus bulkStatus;
    private Integer bulkSize;
    private Boolean autoSend;
    private Integer voiceDuration;
}
