package com.project.portnet_be.domain.carrer;

import com.project.portnet_be.dto.popol.carrer.Carrer;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CARRER")
@Getter
public class CarrerEntity {

    @Id @GeneratedValue
    @Column(name = "CARRER_ID")
    private Long carrerId;

    @Column(name = "POPOL_ID")
    private Long popolId;

    @Column(name = "START_DT")
    private Date startDt;

    @Column(name = "END_DT")
    private Date endDt;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "CONTENTS")
    private String contents;

    public CarrerEntity() {
    }

    @Builder
    public CarrerEntity(Long popolId, Date startDt, Date endDt, String company, String department, String contents) {
        this.popolId = popolId;
        this.startDt = startDt;
        this.endDt = endDt;
        this.company = company;
        this.department = department;
        this.contents = contents;
    }

    public void updateCarrer(Carrer carrer){
        this.startDt = carrer.getStartDt();
        this.endDt = carrer.getEndDt();
        this.company = carrer.getCompany();
        this.department = carrer.getDepartment();
        this.contents = carrer.getContents();
    }

}
