package com.scott;

import com.scott.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyEladminBackend1Application {

    public static void main(String[] args) {
        SpringApplication.run(MyEladminBackend1Application.class, args);
        System.out.println("http://localhost:8080");
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
