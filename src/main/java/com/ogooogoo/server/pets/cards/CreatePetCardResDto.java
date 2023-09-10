package com.ogooogoo.server.pets.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ogooogoo.server.pets.category.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreatePetCardResDto {
    private Type type;
    private String name;
    @JsonProperty("img_url")
    private String imgUrl;

    @Builder
    public CreatePetCardResDto(Type type, String name, String imgUrl) {
        this.type = type;
        this.name = name;
        this.imgUrl = imgUrl;
    }
}
