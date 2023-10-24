package com.project.portnet_be.domain.carrer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrerRepository extends JpaRepository<CarrerEntity, Long> {

    Optional<CarrerEntity> findByPopolId(Long popolId);

}
