package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PetBusinessCardService {

    private final PetBusinessCardRepository petBusinessCardRepository;

    public ResponseEntity<?> createPetBusinessCard(PetBusinessCardRequestDto petBusinessCardRequestDto, KakaoTokenInfo info) {

        // Access Token 을 사용해 kakaoId 가져오기
        Long userId = info.getId();

        // 카드 개수 확인 > 2개 초과할 경우 Exception 발생
        List<PetBusinessCardResponseDto> petBusinessCards = petBusinessCardRepository.findAllByUserId(userId);
        if (petBusinessCards.size() > 2) {
            throw new IllegalStateException("펫명함은 최대 2개까지 생성할 수 있습니다");
        }

        // 알레르기 없음 체크했는데, 세부 항목이 넘어올 경우 > 알레르기 세부 항목 null 값으로 변경
        petBusinessCardRequestDto.validation();

        // 배열로 들어오는 값(5개 항목) 중복 항목 있는지 체크
        if (hasDuplicates(petBusinessCardRequestDto)) {
            throw new IllegalArgumentException("선택 항목(알레르기 세부 항목, 반려동물 성격, 반려동물 취향) 중 중복된 값이 있습니다");
        }

        // 펫명함 생성
        PetBusinessCardEntity petBusinessCard = new PetBusinessCardEntity(petBusinessCardRequestDto, userId);
        petBusinessCardRepository.save(petBusinessCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updatePetBusinessCard(Long petBusinessCardId, PetBusinessCardRequestDto petBusinessCardRequestDto, KakaoTokenInfo info) {

        PetBusinessCardEntity petBusinessCard = petBusinessCardRepository.findById(petBusinessCardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명함입니다"));

        if (petBusinessCard.getUserId().equals(info.getId())) {
            petBusinessCard.update(petBusinessCardRequestDto);
        } else {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deletePetBusinessCard(Long petBusinessCardId, KakaoTokenInfo info) {
        PetBusinessCardEntity petBusinessCard = petBusinessCardRepository.findById(petBusinessCardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명함입니다"));

        if (petBusinessCard.getUserId().equals(info.getId())) {
            petBusinessCardRepository.deleteById(petBusinessCardId);
        } else {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getMyBusinessCards(KakaoTokenInfo info) {
        List<PetBusinessCardResponseDto> businessCards = petBusinessCardRepository.findAllByUserId(info.getId());
        if(businessCards != null) {
            return new ResponseEntity<>(businessCards, HttpStatus.OK);
        } else {
            throw new IllegalStateException("명함을 불러올 수 없습니다");
        }
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