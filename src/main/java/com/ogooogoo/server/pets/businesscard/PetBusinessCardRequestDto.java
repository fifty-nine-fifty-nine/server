package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.pets.annotation.Duplication;
import com.ogooogoo.server.pets.category.*;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Builder
public class PetBusinessCardRequestDto {

    @NotBlank
    Type type;

    @NotBlank
    String petName;

    @NotBlank
    Gender gender;

    @NotBlank
    String petProfileImgPath;

    @NotBlank
    int birth;

    @NotBlank
    String species;

    @NotBlank
    boolean neutralization;

    @NotBlank
    boolean allergy;

    @Duplication
    List<MainAllergy> mainAllergy;

    @Duplication
    List<SubAllergy> subAllergy;

    @Duplication
    List<EtcAllergy> etcAllergy;

    String personalityToPerson;

    String personalityAmongAnimals;

    @Duplication
    List<String> petLike;

    @Duplication
    List<String> petHate;

    @NotBlank
    List<String> businesscardImgPath;


    public void validation() {
        if (!this.allergy) {
            this.mainAllergy = null;
            this.subAllergy = null;
            this.etcAllergy = null;
        }
    }
}
