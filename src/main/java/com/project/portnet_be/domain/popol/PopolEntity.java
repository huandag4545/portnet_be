package com.project.portnet_be.domain.popol;

import com.project.portnet_be.dto.popol.popol.Popol;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "POPOL")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class PopolEntity {

    @Id @GeneratedValue()
    @Column(name = "POPOL_ID")
    private Long popolId;

    @Column(name = "JOBS")
    private String jobs;

    @Column(name = "DEV_STACK")
    private String devStack;

    @Column(name = "DEV_SITE")
    private String devSite;

    @Column(name = "RCV_CHANNER")
    private String rcvChanner;

    @Column(name = "USER_ID")
    private Long userId;

    @CreatedDate
    @Column(name = "CREAT_DT")
    private Date creatDt;

    @LastModifiedDate
    @Column(name = "UPDT_DT")
    private Date updtDt;

    public PopolEntity() {
    }

    @Builder
    public PopolEntity(String jobs, String devStack, String devSite, String rcvChanner, Long userId, LocalDateTime creatDt, LocalDateTime updtDt) {
        this.jobs = jobs;
        this.devStack = devStack;
        this.devSite = devSite;
        this.rcvChanner = rcvChanner;
        this.userId = userId;
    }

    public void updatePopol(Popol popol){
        this.jobs = popol.getJobs();
        this.devStack = popol.getDevStack();
        this.devSite = popol.getDevSite();
        this.rcvChanner = popol.getRcvChanner();
    }
}
