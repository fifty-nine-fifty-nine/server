package com.ogooogoo.server.pets.cards;

import com.ogooogoo.server.clients.dall_e.DallEClient;
import com.ogooogoo.server.clients.dall_e.DallEResponse;
import com.ogooogoo.server.clients.gpt.GptClient;
import com.ogooogoo.server.clients.gpt.GptResult;
import com.ogooogoo.server.clients.naver.NaverClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetCardServices {
    private final DallEClient dallEClient;
    private final GptClient gptClient;

    private final NaverClient naverClient;

    public CreatePetCardResDto generate(CreatePetCardReqDto req) {
        String translatedText = naverClient.translateToEng(req.getDescription());
        GptResult gptResult = gptClient.getGptResponse(translatedText);
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

