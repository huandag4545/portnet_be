package com.project.portnet_be.domain.popol;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PopolRepository extends JpaRepository<PopolEntity, Long> {

    Optional<PopolEntity> findByUserId(Long userId);

    Optional<PopolEntity> findByPopolId(Long popolId);

}
