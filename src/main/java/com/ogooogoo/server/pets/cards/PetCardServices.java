package com.ogooogoo.server.pets.cards;

import com.ogooogoo.server.clients.dall_e.DallEClient;
import com.ogooogoo.server.clients.dall_e.DallEResponse;
import com.ogooogoo.server.clients.gpt.GptClient;
import com.ogooogoo.server.clients.gpt.GptResult;
import org.springframework.stereotype.Service;

@Service
public class PetCardServices {
    private final DallEClient dallEClient;
    private final GptClient gptClient;

    public PetCardServices(DallEClient dallEClient, GptClient gptClient) {
        this.dallEClient = dallEClient;
        this.gptClient = gptClient;
    }

    public CreatePetCardResDto generate(CreatePetCardReqDto req) {
        GptResult gptResult = gptClient.getGptResponse(req.getDescription());
        DallEResponse dallEResponse = dallEClient.generateImage(gptResult);

        String imgUrl = dallEResponse
                .getUrl()
                .orElseThrow(() -> new RuntimeException("이미지 url을 가져올 수 없습니다."));

        return CreatePetCardResDto.builder()
                .name(req.getName())
                .type(req.getType())
                .imgUrl(imgUrl)
                .build();
    }
}

