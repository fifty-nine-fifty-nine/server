package com.ogooogoo.server.pets.businesscard;

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

    public ResponseEntity<?> createPetBusinessCard(PetBusinessCardRequestDto petBusinessCardRequestDto) {

        // 로그인 여부 체크 (카카오 로그인 구현 후 작성 예정)

        // 정상 로그인일 경우, email 받아서 카드 개수 확인 > 2개 초과할 경우 Exception 발생 (카카오 로그인 구현 후 작성 예정)

        // 알레르기 없음 체크했는데, 세부 항목이 넘어올 경우 > 알레르기 세부 항목 null 값으로 변경
        petBusinessCardRequestDto.validation();

        // 배열로 들어오는 값(5개 항목) 중복 항목 있는지 체크
        if (hasDuplicates(petBusinessCardRequestDto)) {
            throw new IllegalArgumentException("선택 항목(알레르기 세부 항목, 반려동물 성격, 반려동물 취향) 중 중복된 값이 존재합니다.");
        }

        // 펫명함 생성
        PetBusinessCardEntity petBusinessCard = new PetBusinessCardEntity(petBusinessCardRequestDto);
        petBusinessCardRepository.save(petBusinessCard);
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