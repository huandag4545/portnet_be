package com.project.portnet_be.dto.popol.popol;

import com.project.portnet_be.domain.popol.PopolEntity;

import java.util.Date;

/**
 * portpolio Request Dto
 * */
public class Popol {

    private Long popolId;

    private String jobs;
    private String devStack;
    private String devSite;

    private String rcvChanner;

    private Long userId;

    private Date creatDt;

    private Date updtDt;

    public Popol() {
    }

    public Popol(PopolEntity poPolEntity) {
        this.popolId = poPolEntity.getPopolId();
        this.jobs = poPolEntity.getJobs();
        this.devStack = poPolEntity.getDevStack();
        this.devSite = poPolEntity.getDevSite();
        this.rcvChanner = poPolEntity.getRcvChanner();
        this.userId = poPolEntity.getUserId();
        this.creatDt = poPolEntity.getCreatDt();
        this.updtDt = poPolEntity.getUpdtDt();
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getDevStack() {
        return devStack;
    }

    public void setDevStack(String devStack) {
        this.devStack = devStack;
    }

    public String getDevSite() {
        return devSite;
    }

    public void setDevSite(String devSite) {
        this.devSite = devSite;
    }

    public String getRcvChanner() {
        return rcvChanner;
    }

    public void setRcvChanner(String rcvChanner) {
        this.rcvChanner = rcvChanner;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPopolId() {
        return popolId;
    }

    public void setPopolId(Long popolId) {
        this.popolId = popolId;
    }

    public Date getCreatDt() {
        return creatDt;
    }

    public void setCreatDt(Date creatDt) {
        this.creatDt = creatDt;
    }

    public Date getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }
}
