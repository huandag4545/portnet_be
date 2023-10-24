package com.project.portnet_be.config.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증되지 않은 사용자가 보호된 리소스에 접근하려고 시도할때 발생되는 예외처리
 * */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("인가되지않은 사용자의 접근이 발견되었습니다." + request.getRemoteAddr());

        log.info(request.getHeader("X-Forwarded-For"));
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
