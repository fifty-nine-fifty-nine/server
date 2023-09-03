package com.ogooogoo.server.clients.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;
import java.util.Map;

@Component
public class GptClient {
    private final String apiEndpoint;
    private final String authToken;
    private final String prompt;
    private static final Logger logger = LoggerFactory.getLogger(GptClient.class);

    public GptClient(
            @Value("${gpt.api.endpoint}") String apiEndpoint,
            @Value("${gpt.auth.token}") String authToken,
            @Value("${gpt.prompt}") String prompt) {

        this.apiEndpoint = apiEndpoint;
        this.authToken = authToken;
        this.prompt = prompt;
    }

    public GptResult getGptResponse(String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = buildHeaders();
        Map<String, Object> requestBody = buildBody(body);

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(apiEndpoint, HttpMethod.POST, req, Map.class);

        return generateResult(response.getBody());
    }

    private HttpHeaders buildHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authToken);
        headers.add("Content-Type", "application/json");
        return headers;
    }

    private Map<String, Object> buildBody(String body){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("temperature", 0);
        requestBody.put("max_tokens", 256);
        requestBody.put("top_p", 0);
        requestBody.put("frequency_penalty", 0);
        requestBody.put("presence_penalty", 0);

        Map<String, Object> message1 = new HashMap<>();
        message1.put("role", "system");
        message1.put("content", prompt);

        Map<String, Object> message2 = new HashMap<>();
        message2.put("role", "user");
        message2.put("content", body);

        requestBody.put("messages", List.of(message1, message2));
        return requestBody;
    }

    private GptResult generateResult(Map<String, Object> responseBody) {
        if(responseBody == null || !responseBody.containsKey("choices"))
            throw new RuntimeException("Invalid responseBody: 'choices' key is missing.");

        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
        if (choices.isEmpty())
            throw new RuntimeException("No choices available in the responseBody.");

        Map<String, Object> firstChoice = choices.get(0);
        Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
        String content = (String) message.get("content");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, GptResult.class);
        } catch (Exception e) {
            logger.error(content);
            throw new RuntimeException("Error parsing the content into GptResult.", e);
        }
    }
}
