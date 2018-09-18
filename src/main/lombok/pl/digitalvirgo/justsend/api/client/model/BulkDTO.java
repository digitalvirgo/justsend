/*
 *  @(#)BulkDTO.java
 *
 *  Copyright 2010 Avantis Mobile Media Group. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;

import java.util.Date;

/**
 * The <code>BulkDTO</code> class TODO add description
 *
 * @author created by: Arkadiusz Wrzosk
 * @author last changed by: $Author$
 * @version $Rev$ $Date$
 */
@Data

public class BulkDTO extends BaseBulkDTO {

    private static final long serialVersionUID = 1L;

    private String name;
    private Date estimatedEndDate;
    private Date endDate;
    private Integer micro;
    private String groupIds;
    private String grossCostAsString;
    private String nettCostAsString;
    private Boolean personalizedMessage;
    private Integer messageCount;
    private Boolean autoSend;
    private InfobipVoiceLanguage language;
    private Integer voiceDuration;
    private Boolean bulkReturnPoints;
    private Boolean bulkCalculation;
    private Boolean confirmationResponse;
}