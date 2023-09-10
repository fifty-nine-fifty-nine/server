package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.pets.category.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class PetBusinessCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petBusinessCardId;

    @Column
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private String petName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String petProfileImgPath;

    @Column(nullable = false)
    private int birthYear;

    @Column(nullable = false)
    private int birthMonth;

    @Column(nullable = false)
    private int birthDay;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Species species;

    @Column(nullable = false)
    private boolean neutralization;

    @Column(nullable = false)
    private boolean allergy;

    @Column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MainAllergy> mainAllergy;

    @Column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<SubAllergy> subAllergy;

    @Column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<EtcAllergy> etcAllergy;

    @Column
    private String personalityToPerson;

    @Column
    private String personalityAmongAnimals;

    @Column
    @ElementCollection
    private List<String> petLike;

    @Column
    @ElementCollection
    private List<String> petHate;

    @Builder
    public PetBusinessCardEntity(PetBusinessCardRequestDto petBusinessCardRequestDto, Long usedId) {
        this.userId = usedId;
        this.type = petBusinessCardRequestDto.getType();
        this.petName = petBusinessCardRequestDto.getPetName();
        this.gender = petBusinessCardRequestDto.getGender();
        this.petProfileImgPath = petBusinessCardRequestDto.getPetProfileImgPath();
        this.birthYear = petBusinessCardRequestDto.getBirthYear();
        this.birthMonth = petBusinessCardRequestDto.getBirthMonth();
        this.birthDay = petBusinessCardRequestDto.getBirthDay();
        this.species = petBusinessCardRequestDto.getSpecies();
        this.neutralization = petBusinessCardRequestDto.isNeutralization();
        this.allergy = petBusinessCardRequestDto.isNeutralization();
        this.mainAllergy = petBusinessCardRequestDto.getMainAllergy();
        this.subAllergy = petBusinessCardRequestDto.getSubAllergy();
        this.etcAllergy = petBusinessCardRequestDto.getEtcAllergy();
        this.personalityToPerson = petBusinessCardRequestDto.getPersonalityToPerson();
        this.personalityAmongAnimals = petBusinessCardRequestDto.getPersonalityAmongAnimals();
        this.petLike = petBusinessCardRequestDto.getPetLike();
        this.petHate = petBusinessCardRequestDto.getPetHate();
    }

}
