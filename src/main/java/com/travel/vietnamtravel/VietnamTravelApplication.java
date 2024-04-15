package com.travel.vietnamtravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VietnamTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(VietnamTravelApplication.class, args);
    }

}
