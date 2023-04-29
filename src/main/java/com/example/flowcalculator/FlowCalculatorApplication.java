package com.example.flowcalculator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Martins"))
@ConfigurationPropertiesScan
@EnableAsync
public class FlowCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowCalculatorApplication.class, args);
    }

}
