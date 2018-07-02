/*
 *  @(#)BaseBulk.java
 *
 *  Copyright 2015 Avantis Mobile Media Group. All rights reserved.
 */
package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.avantis.justsend.api.client.services.impl.enums.BulkVariant;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseBulk extends BaseModel {

    private String name;

    private List<String> to;

    private String from;

    private String message;

    private String sendDate;

    private BulkVariant bulkVariant;

    private boolean personalizedMessage = false;

    private LanguageMessage language;

}