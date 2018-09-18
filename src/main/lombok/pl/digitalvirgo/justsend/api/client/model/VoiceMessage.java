/*
 * Copyright 2018 Digital Virgo S. A. All rights reserved.
 */
package pl.digitalvirgo.justsend.api.client.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VoiceMessage extends Message {

    private LanguageMessage languageMessage;
    private String audioFileUrl;
}