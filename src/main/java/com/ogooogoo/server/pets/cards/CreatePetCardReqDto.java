package com.ogooogoo.server.pets.cards;

import com.ogooogoo.server.pets.category.Classfication;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class CreatePetCardReqDto {
    @NotBlank
    private Classfication type;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @Builder
    public CreatePetCardReqDto(Classfication type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }
}
