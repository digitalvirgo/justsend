/*
 *  @(#)Bulk.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Bulk extends BaseBulk {

    private Long groupId;


}