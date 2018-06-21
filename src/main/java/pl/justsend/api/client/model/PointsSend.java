/*
 * Copyright 2017 Digital Virgo S. A. All rights reserved.
 */
package pl.justsend.api.client.model;

/**

 * User: dbajno
 * Date: 11.09.2017
 * Time: 11:48

 */
public class PointsSend extends BaseModel {

    private Boolean isPointsToSend;
    private Long missingPoints;

    public PointsSend(Boolean isPointsToSend) {
        this.isPointsToSend = isPointsToSend;
    }

    public PointsSend(Boolean isPointsToSend, Long missingPoints) {
        this.isPointsToSend = isPointsToSend;
        this.missingPoints = missingPoints;
    }

    public Boolean isPointsToSend() {
        return isPointsToSend;
    }

    public void setPointsTosend(Boolean pointsToSend) {
        isPointsToSend = pointsToSend;
    }

    public Long getMissingPoints() {
        return missingPoints;
    }

    public void setMissingPoints(Long missingPoints) {
        this.missingPoints = missingPoints;
    }
}