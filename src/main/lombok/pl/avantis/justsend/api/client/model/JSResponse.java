package pl.avantis.justsend.api.client.model;

import lombok.Data;
import pl.avantis.justsend.api.client.services.impl.enums.JSResponseCode;

@Data
public class JSResponse {

    private JSResponseCode responseCode;
    private int errorId;
    private String message;
    private String additionalData;
}
