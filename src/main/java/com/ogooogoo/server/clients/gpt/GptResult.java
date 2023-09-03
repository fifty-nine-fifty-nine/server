package com.ogooogoo.server.clients.gpt;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class GptResult {

    @JsonProperty("Breed")
    private String breed;

    @JsonProperty("Adjective")
    private String adjective;

    public GptResult(String breed, String adjective) {
        this.breed = breed;
        this.adjective = adjective;
    }

    @Override
    public String toString() {
        return "GptResult{" +
                "breed='" + breed + '\'' +
                ", adjective='" + adjective + '\'' +
                '}';
    }
}
