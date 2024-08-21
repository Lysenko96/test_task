package com.lysenko.testtaskapi;

import org.springframework.boot.SpringApplication;

public class TestTesttaskapiApplication {

    public static void main(String[] args) {
        SpringApplication.from(TesttaskapiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
