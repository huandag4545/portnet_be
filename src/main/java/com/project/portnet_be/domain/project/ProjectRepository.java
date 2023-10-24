package com.project.portnet_be.domain.project;

import com.project.portnet_be.domain.carrer.CarrerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByPopolId(Long popolId);


}
