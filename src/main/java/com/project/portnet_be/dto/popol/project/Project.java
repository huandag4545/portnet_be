package com.project.portnet_be.dto.popol.project;

import com.project.portnet_be.domain.project.ProjectEntity;

import java.util.Date;

public class Project {

    private Long projectId;
    private Long popolId;
    private Date startDt;
    private Date finishDt;

    private String title;

    private String contents;

    private String link;

    private String useStack;

    public Project() {
    }

    public Project(ProjectEntity projectEntity) {
        this.projectId = projectEntity.getProjectId();
        this.popolId = projectEntity.getPopolId();
        this.startDt = projectEntity.getStartDt();
        this.finishDt = projectEntity.getFinishDt();
        this.title = projectEntity.getTitle();
        this.contents = projectEntity.getContents();
        this.link = projectEntity.getLink();
        this.useStack = projectEntity.getUseStack();
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getPopolId() {
        return popolId;
    }

    public void setPopolId(Long popolId) {
        this.popolId = popolId;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getFinishDt() {
        return finishDt;
    }

    public void setFinishDt(Date finishDt) {
        this.finishDt = finishDt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUseStack() {
        return useStack;
    }

    public void setUseStack(String useStack) {
        this.useStack = useStack;
    }
}
