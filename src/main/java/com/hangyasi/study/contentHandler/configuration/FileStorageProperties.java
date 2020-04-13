package com.hangyasi.study.contentHandler.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
public class FileStorageProperties {

  private String uploadDir;
}
