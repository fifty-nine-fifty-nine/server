package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PetBusinessCardService {

    private final PetBusinessCardRepository petBusinessCardRepository;

    public ResponseEntity<?> createPetBusinessCard(PetBusinessCardRequestDto petBusinessCardRequestDto, KakaoTokenInfo info) {

        // Access Token 을 사용해 kakaoId 가져오기
        Long userId = info.getId();

        // 카드 개수 확인 > 2개 초과할 경우 Exception 발생
        List<PetBusinessCardEntity> petBusinessCards = petBusinessCardRepository.findAllByUserId(userId);
        if (petBusinessCards.size() > 2) {
            throw new IllegalStateException("펫명함은 2개까지 만들 수 있어요! 더 많이 만들 수 있도록 노력할게요 :)");
        }

        // 알레르기 없음 체크했는데, 세부 항목이 넘어올 경우 > 알레르기 세부 항목 null 값으로 변경
        petBusinessCardRequestDto.validation();

        // 배열로 들어오는 값(5개 항목) 중복 항목 있는지 체크
        if (hasDuplicates(petBusinessCardRequestDto)) {
            throw new IllegalArgumentException("선택 항목(알레르기 세부 항목, 반려동물 성격, 반려동물 취향) 중 중복된 값이 있어요");
        }

        // 펫명함 생성
        PetBusinessCardEntity petBusinessCard = new PetBusinessCardEntity(petBusinessCardRequestDto, userId);
        petBusinessCardRepository.save(petBusinessCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updatePetBusinessCard(PetBusinessCardRequestDto petBusinessCardRequestDto, KakaoTokenInfo info) {

        Long userId = info.getId();

        List<PetBusinessCardEntity> petBusinessCards = petBusinessCardRepository.findAllByUserId(userId);
        for (PetBusinessCardEntity petBusinessCard : petBusinessCards) {
            if (petBusinessCard.getPetName() == petBusinessCardRequestDto.getPetName()) {
                petBusinessCard.update(petBusinessCardRequestDto, userId);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    private boolean hasDuplicates(PetBusinessCardRequestDto petBusinessCardRequestDto) {
        List<List<?>> lists = Arrays.asList(
                petBusinessCardRequestDto.getMainAllergy(),
                petBusinessCardRequestDto.getSubAllergy(),
                petBusinessCardRequestDto.getEtcAllergy(),
                petBusinessCardRequestDto.getPetLike(),
                petBusinessCardRequestDto.getPetHate()
        );

        for (List<?> list : lists) {
            if (hasDuplicates(list)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasDuplicates(List<?> list) {
        Set<Object> set = new HashSet<>();
        for (Object item : list) {
            if (set.contains(item)) {
                return true;
            }
            set.add(item);
        }
        return false;
    }
}