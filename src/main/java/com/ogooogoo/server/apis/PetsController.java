package com.ogooogoo.server.apis;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import com.ogooogoo.server.config.security.UnauthorizedResponse;
import com.ogooogoo.server.pets.cards.CreatePetCardReqDto;
import com.ogooogoo.server.pets.cards.CreatePetCardResDto;
import com.ogooogoo.server.pets.cards.PetCardServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/pets")
@Tag(name = "Pets", description = "Pets 카드와 명함 관련 api 입니다.")
public class PetsController {

    private final PetCardServices petCardSvc;

    @GetMapping
    public String test(@AuthenticationPrincipal KakaoTokenInfo info) {
        System.out.println(info);
        return "test";
    }

    @PostMapping("/cards")
    @Operation(
            summary = "Create Pet Cards",
            description = "펫 카드를 만들기 위한 api 입니다.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.HEADER,
                            name = "Authorization",
                            description = "Bearer token",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Create Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreatePetCardResDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "invalid token",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UnauthorizedResponse.class)
                    )
            ),
    })
    public ResponseEntity<CreatePetCardResDto> create(@RequestBody CreatePetCardReqDto req){
        return ResponseEntity.status(HttpStatus.CREATED).body(petCardSvc.generate(req));
    }

}
