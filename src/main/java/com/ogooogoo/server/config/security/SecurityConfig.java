package com.ogooogoo.server.config.security;
import com.ogooogoo.server.clients.kakao.KakaoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

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
                .antMatchers("/", "/api-docs", "/api-docs/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthFilter(kakaoClient), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint);

        return http.build();
    }
}

