package com.ogooogoo.server.config.security;

import com.ogooogoo.server.clients.kakao.KakaoClient;
import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtAuthFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final KakaoClient kakaoClient;

    public JwtAuthFilter(KakaoClient kakaoClient) {
        this.kakaoClient = kakaoClient;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String token = extractAuthToken((HttpServletRequest) request);

        if (token != null) {
            updateAuthentication(token);
        }

        chain.doFilter(request, response);
    }

    private void updateAuthentication(String token) {
        try {
            KakaoTokenInfo info = kakaoClient.getTokenInfo(token);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(info, null, null));
        } catch (Exception e) {
            logger.error("Error during authentication update with token: {}", token);
        }
    }

    private String extractAuthToken(HttpServletRequest request) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7);
    }
}
