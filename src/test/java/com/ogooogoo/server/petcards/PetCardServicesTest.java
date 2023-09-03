package com.ogooogoo.server.petcards;

import com.ogooogoo.server.clients.dall_e.DallEClient;
import com.ogooogoo.server.clients.dall_e.DallEResponse;
import com.ogooogoo.server.clients.gpt.GptClient;
import com.ogooogoo.server.clients.gpt.GptResult;
import com.ogooogoo.server.pets.cards.CreatePetCardReqDto;
import com.ogooogoo.server.pets.cards.CreatePetCardResDto;
import com.ogooogoo.server.pets.cards.PetCardServices;
import com.ogooogoo.server.pets.category.Classfication;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;

class PetCardServicesTest {

    private final GptClient mockGptClient = Mockito.mock(GptClient.class);
    private final DallEClient mockDalleClient = Mockito.mock(DallEClient.class);
    private final PetCardServices svc = new PetCardServices(mockDalleClient, mockGptClient);

    private GptResult createMockGptResult(String breed, String adjective){
        return new GptResult(breed, adjective);
    }

    private DallEResponse createMockDallEResponse(String url){
        Long mockCreated = 1L;
        Map<String, String> mockDataItem = new HashMap<>();
        mockDataItem.put("url", url);

        List<Map<String, String>> mockDataList = new ArrayList<>();
        mockDataList.add(mockDataItem);
        return new DallEResponse(mockCreated, mockDataList);
    }

    @Test()
    void 성공적으로_펫_카드를_만들_수_있다(){
        // given
        CreatePetCardReqDto dto = CreatePetCardReqDto.builder()
                .type(Classfication.강아지)
                .name("몽실이")
                .description("sns를 하며 카페에서 커피를 마시고 셀카를 찍고 있는 풍성한 사모예드")
                .build();

        GptResult mockGptResult = createMockGptResult("Samoyed", "drinking coffee in a cafe and taking a selfie on social media");
        DallEResponse mockDallEResult = createMockDallEResponse("https://mock.url");

        given(mockGptClient.getGptResponse(any())).willReturn(mockGptResult);
        given(mockDalleClient.generateImage(any())).willReturn(mockDallEResult);

        // when
        CreatePetCardResDto res = svc.generate(dto);

        // then
        assertEquals(res.getType(), Classfication.강아지);
        assertEquals(res.getName(), "몽실이");
        assertEquals(res.getImgUrl(), "https://mock.url");
    }

    @Test()
    void 이미지_url을_찾을_수_없는_경우_runtime_exception을_던진다(){
        // given
        CreatePetCardReqDto dto = CreatePetCardReqDto.builder()
                .type(Classfication.강아지)
                .name("몽실이")
                .description("sns를 하며 카페에서 커피를 마시고 셀카를 찍고 있는 풍성한 사모예드")
                .build();

        GptResult mockGptResult = createMockGptResult("Samoyed", "drinking coffee in a cafe and taking a selfie on social media");
        DallEResponse mockDallEResult = new DallEResponse(1L, new ArrayList<>());

        given(mockGptClient.getGptResponse(any())).willReturn(mockGptResult);
        given(mockDalleClient.generateImage(any())).willReturn(mockDallEResult);

        // when
        Exception exception = assertThrows(RuntimeException.class, () -> {
            svc.generate(dto);
        });

        // then
        String expectedMessage = "이미지 url을 가져올 수 없습니다.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}