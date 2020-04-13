package com.hangyasi.study.contentHandler.repository;

import com.hangyasi.study.contentHandler.entity.Content;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A ContentRepository egy jpaRepository interfész, mely alapvető CRUD műveleteket
 * biztosít a {@link Content} entitáshoz.
 */
@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

  List<Content> findBySubjectId(long subjectId);
}
