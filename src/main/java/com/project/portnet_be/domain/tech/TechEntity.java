package com.project.portnet_be.domain.tech;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "TECH")
@Getter
public class TechEntity {

    @Id @GeneratedValue
    @Column(name = "TECH_ID")
    private Long techId;

    @Column(name = "POPOL_ID")
    private Long popolId;

    @Column(name = "TECH")
    private String tech;

    @Builder
    public TechEntity(Long popolId, String tech) {
        this.popolId = popolId;
        this.tech = tech;
    }

}
