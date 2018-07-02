/*
 * Copyright 2018 Digital Virgo S. A. All rights reserved.
 */
package pl.avantis.justsend.api.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class PrefixReservation extends AVBase<Integer> {

    private String name;
    private Boolean reservation;
    private Date dateTo;
}