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
public class PetBusinessCardEntity implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private int birth;

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

    @Column
    @ElementCollection
    private List<String> businesscardImgPath;


    @Builder
    public PetBusinessCardEntity(Long userId, PetBusinessCardRequestDto petBusinessCardRequestDto) {
        this.userId = userId;
        this.type = petBusinessCardRequestDto.getType();
        this.petName = petBusinessCardRequestDto.getPetName();
        this.gender = petBusinessCardRequestDto.getGender();
        this.petProfileImgPath = petBusinessCardRequestDto.getPetProfileImgPath();
        this.birth = petBusinessCardRequestDto.getBirth();
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
        this.businesscardImgPath = petBusinessCardRequestDto.getBusinesscardImgPath();
    }

    public void update(PetBusinessCardRequestDto petBusinessCardRequestDto) {
        this.type = petBusinessCardRequestDto.getType();
        this.petName = petBusinessCardRequestDto.getPetName();
        this.gender = petBusinessCardRequestDto.getGender();
        this.petProfileImgPath = petBusinessCardRequestDto.getPetProfileImgPath();
        this.birth = petBusinessCardRequestDto.getBirth();
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
        this.businesscardImgPath = petBusinessCardRequestDto.getBusinesscardImgPath();
    }

    @Override
    public int compareTo(Object o) {
        PetBusinessCardEntity p = (PetBusinessCardEntity) o;

        if (this.getBirth() < p.getBirth()) {
            return -1;
        } else if (this.getBirth() > p.getBirth()) {
            return 1;
        } else {
            return 0;
        }
    }

}
