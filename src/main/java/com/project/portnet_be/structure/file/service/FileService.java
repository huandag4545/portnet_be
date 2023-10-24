package com.project.portnet_be.structure.file.service;

import com.project.portnet_be.domain.file.FileEntity;
import com.project.portnet_be.domain.file.FileRepository;
import com.project.portnet_be.structure.file.model.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public Long fileUpload(MultipartFile[] uploadFile) throws  IllegalStateException, IOException {

        //추가적으로 로그인 아이디를 가지고오는 로직추가해야함.
        Long fileId = null;

        List<FileModel> fileList = new ArrayList<FileModel>();
        for(MultipartFile file : uploadFile){
            if(!file.isEmpty()){
                FileEntity fileEntity = FileEntity.builder()
                        .fileName(file.getOriginalFilename())
                        .fileType(file.getContentType())
                        .fileSize((int) file.getSize())
                        .build();

                String FilePath = fileEntity.getFileId() + "_" + fileEntity.getFileName();
                fileId = fileEntity.getFileId();
            }

        }

        return fileId;
    }

}
