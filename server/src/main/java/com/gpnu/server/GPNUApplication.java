package com.gpnu.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = "com.gpnu.*")
@EntityScan(basePackages ="com.gpnu.entity")
@EnableJpaRepositories(basePackages ="com.gpnu.repository")
@Slf4j
@EnableScheduling
public class GPNUApplication {
    public static void main(String[] args) {
        SpringApplication.run(GPNUApplication.class);
    }
}
