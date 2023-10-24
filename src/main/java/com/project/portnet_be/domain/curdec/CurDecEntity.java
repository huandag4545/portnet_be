package com.project.portnet_be.domain.curdec;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

// 경력 기술서 테이블
@Entity
@Table(name = "CUR_DEC")
@Getter
public class CurDecEntity {

    @Id @GeneratedValue
    @Column(name = "CURDEC_ID")
    private Long curdecId;

    @Column(name = "POPOL_ID")
    private Long popolId;

    @Column(name = "COMPANY")
    private String company;
    
    @Column(name = "WORK")
    private String work;
    
    @Column(name = "START_DT")
    private Date startDt;
    
    @Column(name = "FINISH_DT")
    private Date finishDt;

    @Builder
    public CurDecEntity(Long popolId, String company, String work, Date startDt, Date finishDt) {
        this.popolId = popolId;
        this.company = company;
        this.work = work;
        this.startDt = startDt;
        this.finishDt = finishDt;
    }

    public void addCurDec(String company, String work, Date startDt, Date finishDt){
        this.company = company;
        this.work = work;
        this.startDt = startDt;
        this.finishDt = finishDt;
    }
}
