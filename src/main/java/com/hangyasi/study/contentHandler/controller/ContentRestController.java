package com.hangyasi.study.contentHandler.controller;

import com.hangyasi.study.contentHandler.service.ContentService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ContentRestController {

  private ContentService contentService;

  public ContentRestController(ContentService contentService) {
    this.contentService = contentService;
  }

  @PostMapping(value = "/uploadFile", consumes = {"multipart/form-data"})
  public ResponseEntity<String> uploadFile(@RequestPart ContentDto dto, @RequestPart("file") MultipartFile file) {
    dto.setFile(file);
    contentService.persistContent(dto);
    return ResponseEntity.ok("File has been saved successfully.");
  }

  @GetMapping("/getFile/{fileName}")
  public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
    Resource file = contentService.getFile(fileName);
    return ResponseEntity.ok(file);
  }

  @GetMapping("/getFiles/{subjectId}")
  public ResponseEntity<ContentPathList> getFiles(@PathVariable long subjectId) {
    return ResponseEntity.ok(contentService.getFileNames(subjectId));
  }
}
