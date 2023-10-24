package com.project.portnet_be.domain.jwt;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenReopsitory extends CrudRepository<RefreshToken, String> {

    Optional<RefreshToken> findByUserId(String userId);
}
