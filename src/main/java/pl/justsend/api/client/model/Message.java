package pl.justsend.api.client.model;

import pl.justsend.api.client.model.enums.BulkVariant;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 29.03.18
 * Time: 12:46
 */
public class Message {

    private String to;

    private String from;

    private String message;

    private BulkVariant bulkVariant;

    @Override
    public String toString() {
        return "Message{" + "to='" + to + '\'' + ", from='" + from + '\'' + ", message='" + message + '\'' + ", bulkVariant=" + bulkVariant + '}';
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BulkVariant getBulkVariant() {
        return bulkVariant;
    }

    public void setBulkVariant(BulkVariant bulkVariant) {
        this.bulkVariant = bulkVariant;
    }
}
