package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.pets.annotation.Duplication;
import com.ogooogoo.server.pets.category.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class PetBusinessCardRequestDto {

    @NotNull
    Type type;

    @NotBlank
    String petName;

    @NotNull
    Gender gender;

    @NotBlank
    String petProfileImgPath;

    @NotNull
    int birth;

    @NotBlank
    String species;

    @NotNull
    boolean neutralization;

    @NotNull
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

    @NotEmpty
    List<String> businesscardImgPath;


    public void validation() {
        if (!this.allergy) {
            this.mainAllergy = null;
            this.subAllergy = null;
            this.etcAllergy = null;
        }
    }
}
