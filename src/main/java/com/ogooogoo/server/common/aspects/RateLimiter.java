package com.ogooogoo.server.common.aspects;

import com.ogooogoo.server.clients.kakao.KakaoTokenInfo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiter {

    private final RedisTemplate<String, Long> redisTemplate;
    private String key = "limit";
    private Long rateLimit = 3L;

    @Around("bean(petsController)")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 유저 아이디 추출
        Long userId = getUserIdFromReq(joinPoint);

        // 2. redis에 유저 아이디 검색
        checkCardCreation(userId);

        // 3. 토탈 카운트 검증
        checkTotalLimit();

        // 4. 유저 T/F 값에 따라 카운트 수정
        updateCount(userId);

        // 5. 만료 시간 설정
        redisTemplate.expire(this.key, 1, TimeUnit.DAYS);

        return joinPoint.proceed();
    }

    private void updateCount(Long userId){
        this.redisTemplate.opsForList().rightPush(this.key, userId);
    }

    private void checkTotalLimit() {
        Long size = this.redisTemplate.opsForList().size(this.key);

        if(size >= rateLimit)
            throw new RuntimeException();
    }

    private void checkCardCreation(Long userId) {
        Long index = this.redisTemplate.opsForList().indexOf(this.key, userId);

        if(index != null)
            throw new RuntimeException();
    }

    private Long getUserIdFromReq(ProceedingJoinPoint joinPoint) {
        //메서드에 들어가는 매개변수 배열을 읽어옴
        Object[] args = joinPoint.getArgs();

        KakaoTokenInfo info = (KakaoTokenInfo) args[0];

        return info.getId();
    }

}
