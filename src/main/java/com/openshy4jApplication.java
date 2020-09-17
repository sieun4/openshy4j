package com;

import com.openshy4j.web.FlavorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class openshy4jApplication {
    public static void main(String[] args) {
        SpringApplication.run(openshy4jApplication.class, args);
    }
}
