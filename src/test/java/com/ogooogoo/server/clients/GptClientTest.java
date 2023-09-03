package com.ogooogoo.server.clients;

import com.ogooogoo.server.clients.dall_e.DallEClient;
import com.ogooogoo.server.clients.dall_e.DallEResponse;
import com.ogooogoo.server.clients.gpt.GptClient;
import com.ogooogoo.server.clients.gpt.GptResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GptClientTest {

    @Autowired
    private GptClient client;

    @Autowired
    private DallEClient dClient;

    @Test
    void gpt_json_파싱_결과(){
        String body = "푸른 바다를 배경으로 걷고 있는 사모예드";

        GptResult result = client.getGptResponse(body);

        System.out.println(result);

        DallEResponse urls = dClient.generateImage(result);

        System.out.println(urls);
    }

}