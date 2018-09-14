/*
 *  @(#)BaseBulkDTO.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.avantis.justsend.api.client.model;

import lombok.Data;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkStatus;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.BulkVariant;

import java.util.Date;

/**
 * The <code>BaseBulkDTO</code> class TODO add description
 *
 * @author created by: Marcin Hawryluk
 * @author last changed by: $Author$
 * @version $Rev$ $Date$
 */
@Data
public abstract class BaseBulkDTO extends AVBase<Long> {

    protected Date createDate;
    protected Date startDate;
    protected Date sendDate;
    protected String bulkContent;
    protected String sender;
    protected BulkStatus bulkStatus;
    protected BulkVariant bulkVariant;
    protected Long costInPoints;
    protected Long dayPriceInPoints;
    protected Integer sentRbr;
    protected Float grossCost;
    protected Float nettCost;
    protected Integer userId;
    protected BulkSource bulkSource;

}