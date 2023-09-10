package com.ogooogoo.server.pets;

import com.ogooogoo.server.pets.businesscard.PetBusinessCardEntity;
import com.ogooogoo.server.pets.businesscard.PetBusinessCardRepository;
import com.ogooogoo.server.pets.businesscard.PetBusinessCardRequestDto;
import com.ogooogoo.server.pets.businesscard.PetBusinessCardService;
import com.ogooogoo.server.pets.category.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PetBusinessCardServiceTest {

    @Autowired
    PetBusinessCardService petBusinessCardService;

    @Autowired
    PetBusinessCardRepository petBusinessCardRepository;

    @Test
    void 펫명함_생성_성공() {
        //given
        PetBusinessCardRequestDto petBusinessCardRequestDto = PetBusinessCardRequestDto.builder()
                .classfication(Classfication.valueOf("강아지")).petName("백구")
                .gender(Gender.valueOf("수컷")).petProfileImgPath("/images/whitedog")
                .birthYear(2023).birthMonth(8).birthDay(15)
                .species(Species.valueOf("허스키")).neutralization(false).allergy(false)
                .mainAllergy(List.of(MainAllergy.valueOf("소"), MainAllergy.valueOf("닭"), MainAllergy.valueOf("닭")))
                .subAllergy(List.of(SubAllergy.valueOf("계란"), SubAllergy.valueOf(("멸치"))))
                .etcAllergy(List.of(EtcAllergy.valueOf("콩"), EtcAllergy.valueOf("귀리")))
                .personalityToPerson("사람을 좋아해요").personalityAmongAnimals("강아지 사이에서는 적응을 잘 못하고, 고양이를 좋아해요")
                .petLike(List.of("공놀이를 좋아해요")).petHate(List.of("머리 쓰다듬기를 싫어해요"))
                .build();

        PetBusinessCardEntity petBusinessCard = new PetBusinessCardEntity(petBusinessCardRequestDto);

        //when
        PetBusinessCardEntity saveResult = petBusinessCardRepository.save(petBusinessCard);

        //then
        assertEquals(saveResult, petBusinessCard);
    }

    @Test
    void 명함_2개_초과_생성시_에러_반환() {

    }

    @Test
    void 알러지_여부_없을때_세부_알러지_항목_요청시_에러_반환() {

    }

    @Test
    void 알러지_중복_입력시_에러_반환() {

    }

}