package com.ogooogoo.server.clients.dall_e;

import com.ogooogoo.server.clients.gpt.GptClient;
import com.ogooogoo.server.clients.gpt.GptResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class DallEClient {
    private final String apiEndpoint;
    private final String authToken;
    private final String prompt;
    private static final Logger logger = LoggerFactory.getLogger(GptClient.class);

    public DallEClient(
            @Value("${dall-e.api.endpoint}") String apiEndpoint,
            @Value("${dall-e.auth.token}") String authToken,
            @Value("${dall-e.prompt}") String prompt) {

        this.apiEndpoint = apiEndpoint;
        this.authToken = authToken;
        this.prompt = prompt;
    }

    public DallEResponse generateImage(GptResult gptResult) {
        RestTemplate restTemplate = new RestTemplate();

        String finalPrompt = prompt.replace("[Breed]", gptResult.getBreed()).replace("[Adjective]", gptResult.getAdjective());

        HttpHeaders headers = buildHeaders();
        Map<String, Object> requestBody = buildBody(finalPrompt);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try{
            ResponseEntity<DallEResponse> response = restTemplate.exchange(apiEndpoint, HttpMethod.POST, entity, DallEResponse.class);
            return response.getBody();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    private HttpHeaders buildHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authToken);
        headers.add("Content-Type", "application/json");
        return headers;
    }

    private Map<String, Object> buildBody(String body){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", body);
        requestBody.put("n", 1);
        requestBody.put("size", "512x512");
        return requestBody;
    }
}
