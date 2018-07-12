/*
 *  @(#)Group.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseModel {

    private long groupId;
    private String name;
    private List<String> members;
    private Date dateFrom;
    private Date dateTo;



}
