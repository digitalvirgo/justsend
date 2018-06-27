package pl.justsend.api.client.model;

import lombok.Data;
import pl.justsend.api.client.model.enums.JSResponseCode;

@Data
public class JSResponse {

    private JSResponseCode responseCode;
    private int errorId;
    private String message;
    private String additionalData;
}
