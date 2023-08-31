package com.ogooogoo.server.pets.businesscard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PetBusinessCardController {

    private final PetBusinessCardService petBusinessCardService;

    @PostMapping("/cards")
    public ResponseEntity<?> createPetBusinessCard(@Validated @RequestBody PetBusinessCardRequestDto petBusinessCardRequestDto) {
        return petBusinessCardService.createPetBusinessCard(petBusinessCardRequestDto);
    }

}
