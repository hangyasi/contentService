package com.hangyasi.study.contentHandler.service;

import com.hangyasi.study.contentHandler.controller.ContentDto;
import com.hangyasi.study.contentHandler.controller.ContentPathList;
import com.hangyasi.study.contentHandler.entity.Content;
import com.hangyasi.study.contentHandler.repository.ContentRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContentService {

  private ContentRepository repository;
  private StorageService storageService;

  public ContentService(ContentRepository repository, StorageService storageService) {
    this.repository = repository;
    this.storageService = storageService;
  }

  public void persistContent(ContentDto dto) {
    MultipartFile file = dto.getFile();
    storageService.storeContent(file);
    Content content = new Content();
    content.setUploadedById(dto.getUploadedById());
    content.setSubjectId(dto.getSubjectId());
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    content.setContentName(fileName);
    repository.save(content);
  }

  public ContentPathList getFileNames(long subjectId) {
    List<String> fileNames = Optional.ofNullable(repository.findBySubjectId(subjectId))
        .map(Collection::stream)
        .orElseGet(Stream::empty)
        .map(content -> content.getContentName())
        .collect(Collectors.toList());
    ContentPathList pathList = new ContentPathList();
    pathList.setFiles(fileNames);
    return pathList;
  }

  public Resource getFile(String fileName) {
    return storageService.loadFileAsResource(fileName);
  }

}
