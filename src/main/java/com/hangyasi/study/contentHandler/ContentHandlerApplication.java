package com.hangyasi.study.contentHandler;

import com.hangyasi.study.contentHandler.client.NameServerRestClient;
import com.hangyasi.study.contentHandler.client.ServerRequest;
import com.hangyasi.study.contentHandler.property.NameServerProperties;
import com.hangyasi.study.contentHandler.property.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({
		NameServerProperties.class,
		StorageProperties.class,
		ServerRequest.class
})
@EnableScheduling
public class ContentHandlerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ContentHandlerApplication.class, args);
		NameServerRestClient client = ctx.getBean(NameServerRestClient.class);
		client.registerServer();
	}

}
