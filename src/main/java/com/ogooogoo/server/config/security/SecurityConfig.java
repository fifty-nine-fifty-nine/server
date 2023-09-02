package com.ogooogoo.server.config.security;
import com.ogooogoo.server.clients.kakao.KakaoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final KakaoClient kakaoClient;
    private final UnauthorizedEntryPoint unauthorizedEntryPoint;

    public SecurityConfig(KakaoClient kakaoClient, UnauthorizedEntryPoint unauthorizedEntryPoint) {
        this.kakaoClient = kakaoClient;
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/", "/api-docs", "/api-docs/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthFilter(kakaoClient), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));  // Use patterns instead of specific origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

