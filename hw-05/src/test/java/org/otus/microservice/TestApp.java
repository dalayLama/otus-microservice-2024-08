package org.otus.microservice;

import org.springframework.boot.SpringApplication;

public class TestApp {

    public static void main(String[] args) {
        SpringApplication.from(App::main)
                .with(ContainersConfiguration.class)
                .run(args);
    }

}
