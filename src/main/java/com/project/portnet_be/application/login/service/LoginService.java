package com.project.portnet_be.application.login.service;


import com.project.portnet_be.application.login.model.LoginModel;
import com.project.portnet_be.config.security.jwt.JwtProvider;
import com.project.portnet_be.config.security.jwt.JwtToken;
import com.project.portnet_be.domain.user.User;
import com.project.portnet_be.domain.user.UserRepository;
import com.project.portnet_be.dto.login.LoginDto;
import com.project.portnet_be.structure.exception.LoginException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.Base64;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
    public class LoginService {

        private final UserRepository userRepository;
        private final AuthenticationManagerBuilder authenticationManagerBuilder;
        private final JwtProvider jwtProvider;
        private @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public ResponseEntity<JwtToken> login(LoginModel loginModel){

        log.info("Start login");
        User user = userRepository.findByUsername(loginModel.getId()).orElseThrow(()->new IllegalArgumentException("존재하는 계정이없습니다"));

        log.info("Password Checking");
        // 비밀번호 체크로직
        if(!bCryptPasswordEncoder.matches(loginModel.getPwd(), user.getPassword())){
            return new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

        log.info("Create authentication");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginModel.getId(),loginModel.getPwd());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        log.info("Create Token");
        JwtToken jwtToken = jwtProvider.creatToken(authentication, user);

        byte[] nicknameByte = jwtToken.getNickname().getBytes();

        log.info("Header Setting");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ACCESTOKEN", jwtToken.getAccesToken());
        httpHeaders.add("REFRESHTOKEN", jwtToken.getRefreshToken());
        httpHeaders.add("NICKNAME", Base64.getEncoder().encodeToString(nicknameByte));
        httpHeaders.add("USERNAME", jwtToken.getUsername());
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(jwtToken,httpHeaders,HttpStatus.OK);

    }
}
