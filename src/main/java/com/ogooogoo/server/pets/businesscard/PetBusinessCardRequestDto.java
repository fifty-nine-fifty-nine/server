package com.ogooogoo.server.pets.businesscard;

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
    Species species;

    @NotBlank
    boolean neutralization;

    @NotBlank
    boolean allergy;

    List<MainAllergy> mainAllergy;

    List<SubAllergy> subAllergy;

    List<EtcAllergy> etcAllergy;

    String personalityToPerson;

    String personalityAmongAnimals;

    List<String> petLike;

    List<String> petHate;

    public void validation() {
        if (!this.allergy) {
            this.mainAllergy = null;
            this.subAllergy = null;
            this.etcAllergy = null;
        }
    }
}
