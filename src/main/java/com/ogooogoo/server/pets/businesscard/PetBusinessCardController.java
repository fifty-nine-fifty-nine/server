package com.ogooogoo.server.pets.businesscard;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pets")
public class PetBusinessCardController {

    private final PetBusinessCardService petBusinessCardService;

    @PostMapping("/businesscards")
    public ResponseEntity<PetBusinessCardResponseDto> createPetBusinessCard(@Validated @RequestBody PetBusinessCardRequestDto petBusinessCardRequestDto,
                                                   @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.createPetBusinessCard(petBusinessCardRequestDto, info);
    }

    @PutMapping("/businesscards/{id}")
    public ResponseEntity<PetBusinessCardResponseDto> updatePetBusinessCard(@PathVariable Long id,
                                                   @Validated @RequestBody PetBusinessCardRequestDto petBusinessCardRequestDto,
                                                   @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.updatePetBusinessCard(id, petBusinessCardRequestDto, info);
    }

    @DeleteMapping("/businesscards/{id}")
    public ResponseEntity<?> deleteBusinessCard(@PathVariable Long id,
                                                @AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.deletePetBusinessCard(id, info);
    }

    @GetMapping("/mybusinesscards")
    public ResponseEntity<List<PetBusinessCardEntity>> getMyBusinessCards(@AuthenticationPrincipal KakaoTokenInfo info) {
        return petBusinessCardService.getAllMy(info);
    }

}
