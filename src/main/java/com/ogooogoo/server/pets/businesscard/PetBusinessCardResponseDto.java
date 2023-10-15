package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.pets.category.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PetBusinessCardResponseDto {

    Long id;

    Type type;

    String petName;

    Gender gender;

    String petProfileImgPath;

    int birth;

    Species species;

    boolean neutralization;

    boolean allergy;

    List<String> mainAllergy;

    List<String> subAllergy;

    List<String> etcAllergy;

    String personalityToPerson;

    String personalityAmongAnimals;

    List<String> petLike;

    List<String> petHate;

    List<String> businesscardImgPath;


    @Builder
    public PetBusinessCardResponseDto(PetBusinessCardEntity petBusinessCard) {
        this.id = petBusinessCard.getId();
        this.type = petBusinessCard.getType();
        this.petName = petBusinessCard.getPetName();
        this.gender = petBusinessCard.getGender();
        this.petProfileImgPath = petBusinessCard.getPetProfileImgPath();
        this.birth = petBusinessCard.getBirth();
        this.species = petBusinessCard.getSpecies();
        this.neutralization = petBusinessCard.isNeutralization();
        this.allergy = petBusinessCard.isAllergy();
        this.mainAllergy = convertToStringList(petBusinessCard.getMainAllergy());
        this.subAllergy = convertToStringList(petBusinessCard.getSubAllergy());
        this.etcAllergy = convertToStringList(petBusinessCard.getEtcAllergy());
        this.personalityToPerson = petBusinessCard.getPersonalityToPerson();
        this.personalityAmongAnimals = petBusinessCard.getPersonalityAmongAnimals();
        this.petLike = petBusinessCard.getPetLike();
        this.petHate = petBusinessCard.getPetHate();
        this.businesscardImgPath = petBusinessCard.getBusinesscardImgPath();
    }

    private List<String> convertToStringList(List<? extends Enum<?>> enumList) {
        List<String> stringList = new ArrayList<>();
        for (Enum<?> enumValue : enumList) {
            stringList.add(enumValue.name());
        }
        return stringList;
    }
}
