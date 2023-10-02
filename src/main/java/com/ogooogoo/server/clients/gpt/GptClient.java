package com.ogooogoo.server.clients.gpt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GptClient {
    private final String apiEndpoint;
    private final String authToken;
    private static final Logger logger = LoggerFactory.getLogger(GptClient.class);

    public GptClient(
            @Value("${gpt.api.endpoint}") String apiEndpoint,
            @Value("${gpt.auth.token}") String authToken) {

        this.apiEndpoint = apiEndpoint;
        this.authToken = authToken;
    }

    public GptResult getGptResponse(String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = buildHeaders();
        List<Map<String, Object>> msgs = buildMessages(body);
        List<Map<String, Object>> funcs = buildFunctions();
        Map<String, Object> requestBody = buildBody(msgs, funcs);

        HttpEntity<Map<String, Object>> req = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GptResult> response = restTemplate.exchange(
                apiEndpoint,
                HttpMethod.POST,
                req,
                GptResult.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error(response.getBody().toString());
            throw new NullPointerException("Fail to get Breed and Adjective");
        }

        return response.getBody();
    }

    private HttpHeaders buildHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authToken);
        headers.add("Content-Type", "application/json");
        return headers;
    }

    private List<Map<String, Object>> buildMessages(String body){
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "Extract the Breed and Adjective from a given body text and translate to english.");

        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", body);

        return Arrays.asList(systemMessage, userMessage);
    }

    private List<Map<String, Object>> buildFunctions(){
        Map<String, Object> breedProperties = new HashMap<>();
        breedProperties.put("type", "string");
        breedProperties.put("description", "The breed of the animal, e.g., 'Monkey'. if not english, translate it");

        Map<String, Object> adjectiveProperties = new HashMap<>();
        adjectiveProperties.put("type", "string");
        adjectiveProperties.put("description", "The adjective describing the animal, e.g., 'wearing a swimsuit on the beach, under a blue sky'. if not english, translate it");

        Map<String, Object> properties = new HashMap<>();
        properties.put("Breed", breedProperties);
        properties.put("Adjective", adjectiveProperties);

        Map<String, Object> functionParameters = new HashMap<>();
        functionParameters.put("type", "object");
        functionParameters.put("properties", properties);
        functionParameters.put("required", Arrays.asList("Breed", "Adjective"));

        Map<String, Object> function = new HashMap<>();
        function.put("name", "extract_breed_and_adjective");
        function.put("description", "Extract the Breed and Adjective from a given body text.");
        function.put("parameters", functionParameters);

        return Arrays.asList(function);
    }

    private Map<String, Object> buildBody(List<Map<String, Object>> messages, List<Map<String, Object>> functions){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo-0613");
        requestBody.put("messages", messages);
        requestBody.put("functions", functions);
        requestBody.put("function_call", "auto");
        requestBody.put("temperature", 0);
        requestBody.put("max_tokens", 256);
        requestBody.put("top_p", 0);
        requestBody.put("frequency_penalty", 0);
        requestBody.put("presence_penalty", 0);

        return requestBody;
    }
}
