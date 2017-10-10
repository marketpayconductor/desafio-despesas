package com.jarddel.desafio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.jarddel.desafio.api.application.configurations.ApplicationProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperty.class)
public class DesafioDespesasApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioDespesasApplication.class, args);
    }
}
