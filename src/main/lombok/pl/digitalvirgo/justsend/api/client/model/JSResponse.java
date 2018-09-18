package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.JSResponseCode;

@Data
public class JSResponse {

    private JSResponseCode responseCode;
    private int errorId;
    private String message;
    private String additionalData;
}
