package com.project.portnet_be.config.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtProvider jwtProvider;



//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String accessToken = request.getHeader("ACCESTOKEN");
//
//        String path = request.getServletPath();
//        log.info(path);
//
//        if (!path.contains("/swagger-ui/")) {
//            if (accessToken != null && jwtProvider.validateToken(accessToken) == JwtCode.ACCESS) {
//                Authentication authentication = jwtProvider.getAuthentication(accessToken);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else if (accessToken != null && jwtProvider.validateToken(accessToken) == JwtCode.EXPIRED) {
//                String refreshToken = request.getHeader("REFRESHTOKEN");
//                if (refreshToken != null && jwtProvider.validateToken(refreshToken) == JwtCode.ACCESS) {
//                    JwtToken jwtToken = jwtProvider.reissueRefreshToken(refreshToken);
//                    response.setHeader("ACCESTOKEN", jwtToken.getAccesToken());
//                    response.setHeader("REFRESHTOKEN", jwtToken.getRefreshToken());
//                    response.setHeader("NICKNAME", Base64.getEncoder().encodeToString(jwtToken.getNickname().getBytes()));
//                    response.setHeader("USERNAME", jwtToken.getUsername());
//                    Authentication authentication = jwtProvider.getAuthentication(jwtToken.getAccesToken());
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    log.info("Succes reissue Token");
//                }
//            }
//        }
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("ACCESTOKEN");

            if (accessToken != null && jwtProvider.validateToken(accessToken) == JwtCode.ACCESS) {
                Authentication authentication = jwtProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (accessToken != null && jwtProvider.validateToken(accessToken) == JwtCode.EXPIRED) {
                String refreshToken = request.getHeader("REFRESHTOKEN");
                if (refreshToken != null && jwtProvider.validateToken(refreshToken) == JwtCode.ACCESS) {
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        boolean result = EXCLUDE_URL.stream().anyMatch(exclude -> exclude.contains(request.getServletPath()));
//        log.info(request.getServletPath());
//        System.out.println(result);

//        String str1 =
        String uri = request.getRequestURI();
        //로그인이 필요한서비스가 아니라면 true 맞으면 false
        String regex = "/api/user/";
        boolean urlMatch = !uri.contains(regex);
        log.info("uri: " +uri+"/"+urlMatch);

        return urlMatch;

//        // real gogo?????????????????????
//        // // gogo
//        // gogo??????????????
//        // 시발럼들 머가문제냐
// // 머지 스웨거 창 여기로 띄워바
//
//    }
    }
}
