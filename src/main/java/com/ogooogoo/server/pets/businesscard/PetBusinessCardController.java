package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pets")
public class PetBusinessCardController {

    private final PetBusinessCardService petBusinessCardService;

    @PostMapping("/businesscards/{petBusinessCardId}")
    public ResponseEntity<?> createPetBusinessCard(@Validated @RequestBody PetBusinessCardRequestDto petBusinessCardRequestDto,
                                                   @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.createPetBusinessCard(petBusinessCardRequestDto, info);
    }

    @PutMapping("/businesscards")
    public ResponseEntity<?> updatePetBusinessCard(Long petBusinessCardId,
                                                   @Validated @RequestBody PetBusinessCardRequestDto petBusinessCardRequestDto,
                                                   @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.updatePetBusinessCard(petBusinessCardId, petBusinessCardRequestDto, info);
    }

    @DeleteMapping("/businesscards/")
    public ResponseEntity<?> updatePetBusinessCard(Long petBusinessCardId,
                                                   @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.deletePetBusinessCard(petBusinessCardId, info);
    }

}
