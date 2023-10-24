package com.project.portnet_be.structure.file.model;

import java.util.Date;

public class FileModel {

    private Long userId;

    private String fileName;

    private String fileSize;

    private Date uploadDt;

    private String fileType;

    private String status;

    public FileModel(Long userId, String fileName, String fileSize, Date uploadDt, String fileType, String status) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.uploadDt = uploadDt;
        this.fileType = fileType;
        this.status = status;
    }
}
