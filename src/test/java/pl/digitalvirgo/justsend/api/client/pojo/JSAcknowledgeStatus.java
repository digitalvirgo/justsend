/*
 * Copyright 2018 Digital Virgo S. A. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.pojo;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Jakub Zaczek
 * Date: 12.03.2018
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public enum JSAcknowledgeStatus {

    MESSAGE_DELIVERED(0),
    MESSAGE_NOT_DELIVERED(1),
    UNDEFINED(9999),
    DELIVERED(2),
    NOT_DELIVERED(3),
    NO_ANSWER(4),
    BUSY(5),
    UNDELIVERABLE(6);

    private Integer value;

    JSAcknowledgeStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static Optional<JSAcknowledgeStatus> getByValue(Integer value){
        return Arrays.stream(JSAcknowledgeStatus.values()).filter((enumV) -> enumV.getValue().equals(value)).findFirst();
    }
}