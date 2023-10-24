package com.project.portnet_be.domain.dectask;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

// 경력 기술서 테이블
@Entity
@Table(name = "DEC_TASK")
@Getter
public class DecTaskEntity {

    @Id @GeneratedValue
    @Column(name = "TASK_ID")
    private Long taskId;

    @Column(name = "CURDEC_ID")
    private Long curdecId;

    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "CONTENTS")
    private String contents;

    @Column(name = "LINK")
    private String link;

    @Column(name = "START_DT")
    private Date startDt;
    
    @Column(name = "FINISH_DT")
    private Date finishDt;

    @Builder
    public DecTaskEntity(Long curdecId, String title, String contents, String link, Date startDt, Date finishDt) {
        this.curdecId = curdecId;
        this.title = title;
        this.contents = contents;
        this.link = link;
        this.startDt = startDt;
        this.finishDt = finishDt;
    }
}
