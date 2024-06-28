package com.smarthome.platform.upc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpcApplication.class, args);
    }

}
