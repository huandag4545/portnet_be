package com.project.portnet_be.domain.file;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "OCCUPATION")
@Getter
public class FileEntity {

    @Id @GeneratedValue
    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_SIZE")
    private int fileSize;

    @Column(name = "UPLOAD_DT")
    private Date uploadDt;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "STATUS")
    private String status;

    @CreatedDate
    @Column(name = "CREAT_DT")
    private LocalDateTime creatDt;

    @LastModifiedDate
    @Column(name = "UPDT_DT")
    private LocalDateTime updtDt;

    @Builder
    public FileEntity(Long userId, String fileName, int fileSize, Date uploadDt, String fileType, String status) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.uploadDt = uploadDt;
        this.fileType = fileType;
        this.status = status;
    }
}
