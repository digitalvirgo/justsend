/*
 * Copyright 2018 Digital Virgo S. A. All rights reserved.
 */
package pl.justsend.api.client.model;

/**

 * User: Jakub Zaczek
 * Date: 16.04.2018
 * Time: 09:54

 */
public class VoiceMessagePreview extends BaseModel {

    private String message;
    
    private LanguageMessage languageMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LanguageMessage getLanguageMessage() {
        return languageMessage;
    }

    public void setLanguageMessage(LanguageMessage languageMessage) {
        this.languageMessage = languageMessage;
    }
}