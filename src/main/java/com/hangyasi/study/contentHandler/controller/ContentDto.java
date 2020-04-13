package com.hangyasi.study.contentHandler.controller;

import org.springframework.web.multipart.MultipartFile;

public class  ContentDto {

  private long uploadedById;
  private long subjectId;
  private transient MultipartFile file;

  public long getUploadedById() {
    return uploadedById;
  }

  public void setUploadedById(long uploadedById) {
    this.uploadedById = uploadedById;
  }

  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }
}
