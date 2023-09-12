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

    @PostMapping("/businesscards")
    public ResponseEntity<?> createPetBusinessCard(@Validated @RequestBody PetBusinessCardRequestDto petBusinessCardRequestDto,
                                                   @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.createPetBusinessCard(petBusinessCardRequestDto, info);
    }

    @PutMapping("/businesscards/{petBusinessCardId}")
    public ResponseEntity<?> updatePetBusinessCard(@PathVariable Long id,
                                                   @Validated @RequestBody PetBusinessCardRequestDto petBusinessCardRequestDto,
                                                   @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.updatePetBusinessCard(id, petBusinessCardRequestDto, info);
    }

    @DeleteMapping("/businesscards/{petBusinessCardId}")
    public ResponseEntity<?> deleteBusinessCard(@PathVariable Long id,
                                                @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.deletePetBusinessCard(id, info);
    }

    @GetMapping("/mybusinesscards")
    public ResponseEntity<?> getMyBusinessCards(@AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.getAllMy(info);
    }

}
