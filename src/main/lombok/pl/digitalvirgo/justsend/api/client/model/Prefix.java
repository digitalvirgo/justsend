/*
 *  @(#)Prefix.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.PrefixAccessType;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.PrefixType;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class Prefix extends AVBase<Integer> {

    private String name;
    private Integer userId;
    private String userEmail;
    private Date createDate;
    private PrefixType prefixType;
    private PrefixAccessType prefixAccessType;
    private Date dateFrom;
    private Date dateTo;
}