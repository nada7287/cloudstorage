package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class Files {



    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private Blob fileData;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Blob getFileData() {
        return fileData;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }

    public Files(Integer fileId, String filename, String contenttype, String filesize, Integer userid, Blob filedata){

        this.fileId=fileId;
        this.userId=userid;
        this.fileName=filename;
        this.contentType=contenttype;
        this.fileSize=filesize;
        this.fileData=filedata;


    }

}
