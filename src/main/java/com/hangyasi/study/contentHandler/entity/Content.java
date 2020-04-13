package com.hangyasi.study.contentHandler.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A Content egy JPA entitás, mely a feltöltött fileok elérési útját, a kapcsolódó tárgyat,
 * valamint a feltöltő user id-ját tartalmazza.
 */
@Entity
public class Content {

  @Id
  @GeneratedValue
  private long id;
  private long uploadedById;
  private long subjectId;
  private String contentName;

  public long getId() {
    return id;
  }

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

  public String getContentName() {
    return contentName;
  }

  public void setContentName(String contentName) {
    this.contentName = contentName;
  }
}
