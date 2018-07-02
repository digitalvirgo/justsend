package pl.avantis.justsend.api.client.model;

import lombok.Data;
import pl.avantis.justsend.api.client.services.impl.enums.BulkVariant;

@Data
public class Message {

    private String to;

    private String from;

    private String message;

    private BulkVariant bulkVariant;

    public Message() {
    }

    public Message(String to, String from, String message, BulkVariant bulkVariant) {
        this.to = to;
        this.from = from;
        this.message = message;
        this.bulkVariant = bulkVariant;
    }
}
