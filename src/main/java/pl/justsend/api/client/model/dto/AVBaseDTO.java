/*
 *  @(#)AVBaseDTO.java
 *
 *  Copyright 2009 Avantis Mobile Media Group.  All rights reserved.
 */
package pl.justsend.api.client.model.dto;

/**
 * The <code>AVTemplateBaseDTO</code> public
 *
 * @author created by: Michal Ostapowicz on 2009-03-02, 16:26:04
 * @author last changed by: $Author: mhawryluk $
 * @version 1.0 2010-11-22, 15:30:09
 */
public class AVBaseDTO<K> {

    protected K id;

    public K getId() {
        return id;
    }

    public void setId(final K id) {
        this.id = id;
    }

}
