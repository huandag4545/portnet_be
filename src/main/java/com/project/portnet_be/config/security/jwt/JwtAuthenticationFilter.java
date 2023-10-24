package com.project.portnet_be.config.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String accessToken = request.getHeader("ACCESTOKEN");


        if(accessToken != null && jwtProvider.validateToken(accessToken) == JwtCode.ACCESS){
            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else if(accessToken != null && jwtProvider.validateToken(accessToken) == JwtCode.EXPIRED){
            String refreshToken = request.getHeader("REFRESHTOKEN");
            if(refreshToken != null && jwtProvider.validateToken(refreshToken) == JwtCode.ACCESS){
                JwtToken jwtToken = jwtProvider.reissueRefreshToken(refreshToken);
                response.setHeader("ACCESTOKEN", jwtToken.getAccesToken());
                response.setHeader("REFRESHTOKEN", jwtToken.getRefreshToken());
                response.setHeader("NICKNAME", Base64.getEncoder().encodeToString(jwtToken.getNickname().getBytes()));
                response.setHeader("USERNAME", jwtToken.getUsername());
                Authentication authentication = jwtProvider.getAuthentication(jwtToken.getAccesToken());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Succes reissue Token");
            }
        }
    }
}
