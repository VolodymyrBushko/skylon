package org.vbushko.skylon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SkylonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkylonApplication.class, args);
    }
}
