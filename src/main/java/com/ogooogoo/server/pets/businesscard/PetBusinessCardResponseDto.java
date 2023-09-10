package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.pets.category.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PetBusinessCardResponseDto {

    private Long petBusinessCardId;

    Type type;

    String petName;

    Gender gender;

    String petProfileImgPath;

    int birth;

    Species species;

    boolean neutralization;

    boolean allergy;

    List<MainAllergy> mainAllergy;

    List<SubAllergy> subAllergy;

    List<EtcAllergy> etcAllergy;

    String personalityToPerson;

    String personalityAmongAnimals;

    List<String> petLike;

    List<String> petHate;

}
