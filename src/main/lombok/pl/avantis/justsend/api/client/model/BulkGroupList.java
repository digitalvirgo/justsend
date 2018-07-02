/*
 *  @(#)BulkGroupList.java
 *
 *  Copyright 2015 Avantis Mobile Media Group. All rights reserved.
 */
package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BulkGroupList extends BaseBulk {

    private List<Long> groupIds;

}