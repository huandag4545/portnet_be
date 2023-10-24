package com.project.portnet_be.domain.project;

import com.project.portnet_be.dto.popol.project.Project;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "PROJECT")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class ProjectEntity {

    @Id @GeneratedValue()
    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "POPOL_ID")
    private Long popolId;

    @Column(name = "START_DT")
    private Date startDt;

    @Column(name = "FINISH_DT")
    private Date finishDt;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENTS")
    private String contents;

    @Column(name = "LINK")
    private String link;

    @Column(name = "USE_STACK")
    private String useStack;

    public ProjectEntity() {
    }

    @Builder
    public ProjectEntity(Long popolId, Date startDt, Date finishDt, String title, String contents, String link, String useStack) {
        this.popolId = popolId;
        this.startDt = startDt;
        this.finishDt = finishDt;
        this.title = title;
        this.contents = contents;
        this.link = link;
        this.useStack = useStack;
    }

    public void updateProject(Project project){
        this.startDt = project.getStartDt();
        this.finishDt = project.getFinishDt();
        this.title = project.getTitle();
        this.contents = project.getContents();
        this.link = project.getLink();
        this.useStack  = project.getUseStack();
    }
}
