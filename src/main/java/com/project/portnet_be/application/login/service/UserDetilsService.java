package com.project.portnet_be.application.login.service;

import com.project.portnet_be.domain.user.User;
import com.project.portnet_be.domain.user.UserRepository;
import com.project.portnet_be.structure.principal.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserDetilsService implements UserDetailsService {

    private @Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Start UserDetilsService");
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException("존재하는 계정이없습니다"));

        CustomUserDetails userDetails = new CustomUserDetails(user.getUsername(),user.getPassword(),user.getAuthority());
        log.info("Finish UserDetilsService"+ userDetails);

        return userDetails;
    }



}
