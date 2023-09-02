package com.ogooogoo.server.clients.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoTokenInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("app_id")
    private Long appId;

    @Override
    public String toString() {
        return "KakaoTokenInfo{" +
                "id=" + id +
                ", expiresIn='" + expiresIn + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}

