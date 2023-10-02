package com.ogooogoo.server.clients.naver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class NaverClient {

    private final String apiEndpoint;
    private final String clientId;
    private final String clientSecret;

    private static final Logger logger = LoggerFactory.getLogger(NaverClient.class);

    public NaverClient(
            @Value("${naver.papago.endpoint}") String apiEndpoint,
            @Value("${naver.client-id}") String clientId,
            @Value("${naver.client-secret}") String clientSecret
    ) {
        this.apiEndpoint = apiEndpoint;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String translateToEng(String from){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = buildHeaders();
        MultiValueMap<String, String> requestBody = buildBody(from);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(requestBody, headers);

        ResponseEntity<PapagoResponse> response = restTemplate.exchange(
                apiEndpoint,
                HttpMethod.POST,
                req,
                PapagoResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error(response.getBody().toString());
            throw new NullPointerException("Fail to translate");
        }

        return response.getBody().getText();
    }

    private HttpHeaders buildHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", this.clientId);
        headers.add("X-Naver-Client-Secret", this.clientSecret);
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("charset", "UTF-8");
        return headers;
    }

    private MultiValueMap<String, String> buildBody(String body){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("source", "ko");
        requestBody.add("target", "en");
        requestBody.add("text", body);

        return requestBody;
    }
}
