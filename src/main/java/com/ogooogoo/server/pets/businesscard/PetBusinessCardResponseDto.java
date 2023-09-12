package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.pets.category.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PetBusinessCardResponseDto {

    private Long id;

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

    public PetBusinessCardResponseDto(PetBusinessCardEntity petBusinessCard) {
        this.id = petBusinessCard.getId();
        this.petName = petBusinessCard.getPetName();
        this.gender = petBusinessCard.getGender();
        this.petProfileImgPath = petBusinessCard.getPetProfileImgPath();
        this.birth = petBusinessCard.getBirth();
        this.species = petBusinessCard.getSpecies();
        this.neutralization = petBusinessCard.isNeutralization();
        this.allergy = petBusinessCard.isAllergy();
        this.mainAllergy = petBusinessCard.getMainAllergy();
        this.subAllergy = petBusinessCard.getSubAllergy();
        this.etcAllergy = petBusinessCard.getEtcAllergy();
        this.personalityToPerson = petBusinessCard.getPersonalityToPerson();
        this.personalityAmongAnimals = petBusinessCard.getPersonalityAmongAnimals();
        this.petLike = petBusinessCard.getPetLike();
        this.petHate = petBusinessCard.getPetHate();
    }
}
