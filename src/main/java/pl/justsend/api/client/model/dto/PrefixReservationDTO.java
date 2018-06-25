/*
 * Copyright 2018 Digital Virgo S. A. All rights reserved.
 */
package pl.justsend.api.client.model.dto;

import java.util.Date;

/**

 * User: Jakub Zaczek
 * Date: 10.04.2018
 * Time: 13:30

 */
public class PrefixReservationDTO extends AVBaseDTO<Integer> {

    private String name;
    private Boolean reservation;
    private Date dateTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getReservation() {
        return reservation;
    }

    public void setReservation(Boolean reservation) {
        this.reservation = reservation;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}