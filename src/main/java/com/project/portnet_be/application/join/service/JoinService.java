package com.project.portnet_be.application.join.service;

import com.project.portnet_be.application.join.model.JoinModel;
import com.project.portnet_be.domain.user.User;
import com.project.portnet_be.domain.user.UserRepository;
import com.project.portnet_be.dto.join.UserDto;
import com.project.portnet_be.structure.utils.Authority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinService {

    private @Autowired UserRepository userRepository;
    private @Autowired PasswordEncoder passwordEncoder;

    //생성용 객체 생성
    public JoinModel get(){
        JoinModel model = new JoinModel();
        UserDto userDto = new UserDto();
        model.setUserDto(userDto);

        return model;
    }

    public void insert(JoinModel joinModel){
        UserDto userDto = joinModel.getUserDto();

        log.info("Join UserInfo" + userDto);


        User userEntity = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .email(userDto.getEmail())
                .authority("ROLE_USER")
                .build();

        userRepository.save(userEntity);
    }


}
