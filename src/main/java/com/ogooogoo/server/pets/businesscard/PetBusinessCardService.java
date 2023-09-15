package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import com.ogooogoo.server.pets.category.Species;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PetBusinessCardService {

    private final PetBusinessCardRepository petBusinessCardRepository;

    public ResponseEntity<PetBusinessCardResponseDto> createPetBusinessCard(PetBusinessCardRequestDto petBusinessCardRequestDto, KakaoTokenInfo info) {

        Long userId = info.getId();

        List<PetBusinessCardEntity> petBusinessCards = petBusinessCardRepository.findAllByUserId(userId);
        if (petBusinessCards.size() > 2) {
            throw new IllegalStateException("펫명함은 최대 2개까지 생성할 수 있습니다");
        }

        petBusinessCardRequestDto.validation();

        if (hasDuplicates(petBusinessCardRequestDto)) {
            throw new IllegalArgumentException("선택 항목(알레르기 세부 항목, 반려동물 성격, 반려동물 취향) 중 중복된 값이 있습니다");
        }

        PetBusinessCardEntity petBusinessCard = new PetBusinessCardEntity(userId, petBusinessCardRequestDto);
        petBusinessCardRepository.save(petBusinessCard);
        PetBusinessCardResponseDto responseDto = new PetBusinessCardResponseDto(petBusinessCard);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    public ResponseEntity<PetBusinessCardResponseDto> updatePetBusinessCard(Long id, PetBusinessCardRequestDto petBusinessCardRequestDto, KakaoTokenInfo info) {

        PetBusinessCardEntity petBusinessCard = petBusinessCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 펫명함입니다"));

        if (!petBusinessCard.getUserId().equals(info.getId())) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        petBusinessCard.update(petBusinessCardRequestDto);
        PetBusinessCardResponseDto petBusinessCardResponseDto = new PetBusinessCardResponseDto(petBusinessCard);
        return new ResponseEntity<>(petBusinessCardResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePetBusinessCard(Long id, KakaoTokenInfo info) {
        PetBusinessCardEntity petBusinessCard = petBusinessCardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 펫명함입니다"));

        if (!petBusinessCard.getUserId().equals(info.getId())) {
            throw new IllegalArgumentException("잘못된 접근입니다");
        }

        petBusinessCardRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<PetBusinessCardEntity>> getAllMy(KakaoTokenInfo info) {
        List<PetBusinessCardEntity> businessCards = petBusinessCardRepository.findAllByUserId(info.getId());


        if (businessCards == null || businessCards.size() < 1 || businessCards.size() > 2) {
            throw new IllegalStateException("명함을 불러올 수 없습니다");
        }

        Collections.sort(businessCards);

        return new ResponseEntity<>(businessCards, HttpStatus.OK);
    }

    public String getALlSpecies() throws Exception {
        return Species.getAllSpecies();
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