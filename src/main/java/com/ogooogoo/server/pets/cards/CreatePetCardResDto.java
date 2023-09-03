package com.ogooogoo.server.pets.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ogooogoo.server.pets.category.Classfication;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class CreatePetCardResDto {
    private Classfication type;
    private String name;
    @JsonProperty("img_url")
    private String imgUrl;

    @Builder
    public CreatePetCardResDto(Classfication type, String name, String imgUrl) {
        this.type = type;
        this.name = name;
        this.imgUrl = imgUrl;
    }
}
