package com.ogooogoo.server.clients.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class KakaoClient {
    private final String apiEndpoint;

    public KakaoClient(
            @Value("${kakao.auth.api.endpoint}") String apiEndpoint
    ) {
        this.apiEndpoint = apiEndpoint;
    }

    public KakaoTokenInfo getTokenInfo(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = buildHeaders(token);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(null, headers);

        try{
            ResponseEntity<KakaoTokenInfo> response = restTemplate.exchange(apiEndpoint, HttpMethod.GET, request, KakaoTokenInfo.class);
            return response.getBody();
        }catch(Exception exception){
            throw new IllegalArgumentException("인증에 실패했습니다.");
        }
    }

    private HttpHeaders buildHeaders(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-Type", "application/json");
        return headers;
    }
}
