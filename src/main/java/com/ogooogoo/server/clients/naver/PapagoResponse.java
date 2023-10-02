package com.ogooogoo.server.clients.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PapagoResponse {
    private Message message;

    public String getText() {
        return this.message.getResult().getTranslatedText();
    }

}

@Getter @Setter
class Message {
    @JsonProperty("@type")
    private String type;

    @JsonProperty("@service")
    private String service;

    @JsonProperty("@version")
    private String version;

    private Result result;

}

@Getter @Setter
class Result {
    private String translatedText;

}
