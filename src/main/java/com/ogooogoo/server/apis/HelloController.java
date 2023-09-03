package com.ogooogoo.server.apis;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import com.ogooogoo.server.config.security.UnauthorizedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health", description = "Health 체크 api 입니다.")
class HelloController {

    @GetMapping()
    @Operation(summary = "Health Check", description = "서버가 제대로 동작하는지 확인하기 위한 URL 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
    })
    public String hello(){
        return "helloword";
    }

    @GetMapping("/token")
    @Operation(
            summary = "Auth Check",
            description = "Security가 제대로 동작하는지 확인하기 위한 URL 입니다.",
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
                    description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = KakaoTokenInfo.class)
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
    public KakaoTokenInfo tokenInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KakaoTokenInfo info = (KakaoTokenInfo) authentication.getPrincipal();
        return info;
    }
}
