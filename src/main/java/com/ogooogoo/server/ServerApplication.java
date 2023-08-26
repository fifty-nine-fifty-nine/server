package com.ogooogoo.server;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	
}

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
