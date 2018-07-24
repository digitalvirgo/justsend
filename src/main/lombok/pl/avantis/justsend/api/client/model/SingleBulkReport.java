package pl.avantis.justsend.api.client.model;

import pl.avantis.justsend.api.client.services.impl.utils.FloatFormatter;

public class SingleBulkReport extends AVBase<Long> {

    private String sendDay;
    private String email;
    private String accountType;
    private String sender;
    private Long sent;
    private Long notSent;
    private Long points;
    private Float grossCost;
    private String grossCostAsString;
    private Long delivered;
    private Long notDelivered;
    private Integer voiceDuration;

    public String getSendDay() {
        return sendDay;
    }

    public void setSendDay(String sendDay) {
        this.sendDay = sendDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getSent() {
        return sent;
    }

    public void setSent(Long sent) {
        this.sent = sent;
    }

    public Long getNotSent() {
        return notSent;
    }

    public void setNotSent(Long notSent) {
        this.notSent = notSent;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }


    public Float getGrossCost() {
        return grossCost;
    }

    public void setGrossCost(final Float grossCost) {
        this.grossCost = grossCost;
        this.grossCostAsString = FloatFormatter.format(grossCost);
    }

    public String getGrossCostAsString() {
        return grossCostAsString;
    }

    public Long getDelivered() {
        return delivered;
    }

    public void setDelivered(Long delivered) {
        this.delivered = delivered;
    }

    public Long getNotDelivered() {
        return notDelivered;
    }

    public void setNotDelivered(Long notDelivered) {
        this.notDelivered = notDelivered;
    }

    public Integer getVoiceDuration() {
        return voiceDuration;
    }

    public void setVoiceDuration(Integer voiceDuration) {
        this.voiceDuration = voiceDuration;
    }
}