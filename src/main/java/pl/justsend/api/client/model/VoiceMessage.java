/*
 * Copyright 2018 Digital Virgo S. A. All rights reserved.
 */
package pl.justsend.api.client.model;

/**

 * User: Jakub Zaczek
 * Date: 07.03.2018
 * Time: 14:48

 */
public class VoiceMessage extends Message {

    private LanguageMessage languageMessage;

    private String audioFileUrl;

    public LanguageMessage getLanguageMessage() {
        return languageMessage;
    }

    public void setLanguageMessage(LanguageMessage languageMessage) {
        this.languageMessage = languageMessage;
    }

    public String getAudioFileUrl() {
        return audioFileUrl;
    }

    public void setAudioFileUrl(String audioFileUrl) {
        this.audioFileUrl = audioFileUrl;
    }
}