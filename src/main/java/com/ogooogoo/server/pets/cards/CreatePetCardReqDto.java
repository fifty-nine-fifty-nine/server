package com.ogooogoo.server.pets.cards;

import com.ogooogoo.server.pets.category.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
public class CreatePetCardReqDto {
    @NotBlank
    private Type type;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @Builder
    public CreatePetCardReqDto(Type type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }
}
