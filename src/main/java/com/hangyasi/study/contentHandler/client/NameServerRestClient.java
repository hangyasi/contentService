package com.hangyasi.study.contentHandler.client;

import com.hangyasi.study.contentHandler.property.NameServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NameServerRestClient {

  private RestTemplate template;
  private NameServerProperties properties;
  private ServerRequest requestBody;

  public NameServerRestClient(RestTemplateBuilder builder, NameServerProperties properties, ServerRequest requestBody, ResponseErrorHandler
      errorHandler) {
    this.template = builder.errorHandler(errorHandler).build();
    this.properties = properties;
    this.requestBody = requestBody;
  }

  @Scheduled(fixedRate = 30000)
  public void registerServer() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<ServerRequest> request = new HttpEntity<>(requestBody, headers);
    try {
      template.postForEntity(properties.getUrl(), request, String.class);
    } catch(ResourceAccessException exception) {
      log.error("Connection refused: cannot find name server.");
    }

  }
}
