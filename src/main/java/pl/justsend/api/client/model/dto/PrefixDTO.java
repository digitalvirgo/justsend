/*
 *  @(#)PrefixDTO.java
 *
 *  Copyright 2014 Avantis Mobile Media Group. All rights reserved.
 */
package pl.justsend.api.client.model.dto;

import pl.justsend.api.client.model.enums.PrefixAccessType;
import pl.justsend.api.client.model.enums.PrefixType;

import java.util.Date;

public class PrefixDTO extends AVBaseDTO<Integer> {

    private String name;
    private Integer userId;
    private String userEmail;
    private Date createDate;
    private PrefixType prefixType;
    private PrefixAccessType prefixAccessType;
    private Date dateFrom;
    private Date dateTo;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public PrefixType getPrefixType() {
        return prefixType;
    }

    public void setPrefixType(PrefixType prefixType) {
        this.prefixType = prefixType;
    }

    public PrefixAccessType getPrefixAccessType() {
        return prefixAccessType;
    }

    public void setPrefixAccessType(PrefixAccessType prefixAccessType) {
        this.prefixAccessType = prefixAccessType;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}