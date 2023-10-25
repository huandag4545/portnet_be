package com.project.portnet_be.application.login.controller;

import com.project.portnet_be.application.login.model.LoginModel;
import com.project.portnet_be.application.login.service.LoginService;
import com.project.portnet_be.config.security.jwt.JwtToken;
import com.project.portnet_be.dto.login.LoginDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "로그인 컨트롤러")
@RestController
@RequestMapping("/api")
public class LoginRestController {

    private @Autowired LoginService loginService;

    @RequestMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginModel loginModel){
        return loginService.login(loginModel);
    }
}
