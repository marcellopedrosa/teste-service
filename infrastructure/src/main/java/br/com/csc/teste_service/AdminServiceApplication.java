package br.com.csc.admin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
@ComponentScan(basePackages = "br.com.csc.admin_service")
@EntityScan(basePackages = "br.com.csc.admin_service")
@EnableJpaRepositories(basePackages = "br.com.csc.admin_service")
public class AdminServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdminServiceApplication.class, args);
  }
}
