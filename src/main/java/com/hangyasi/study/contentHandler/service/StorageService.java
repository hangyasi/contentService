package com.hangyasi.study.contentHandler.service;

import com.hangyasi.study.contentHandler.property.StorageProperties;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

  private Path storageLocation;

  public StorageService(StorageProperties storageProperties) {
    this.storageLocation = Paths.get(storageProperties.getUploadDir()).toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.storageLocation);
    } catch (IOException ex) {
      // TODO handle exception
    }
  }

  public String storeContent(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      Path targetLocation = this.storageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ex) {
      // TODO handle exception
    }
    return fileName;
  }

  public Resource loadFileAsResource(String fileName) {
    Resource resource;
    try {
      Path filePath = this.storageLocation.resolve(fileName).normalize();
      resource = new UrlResource(filePath.toUri());
      if(resource.exists()) {
        return resource;
      } else {
        // TODO handle
      }
    } catch (MalformedURLException ex) {
      // TODO handle
    }
    return null;
  }

}
