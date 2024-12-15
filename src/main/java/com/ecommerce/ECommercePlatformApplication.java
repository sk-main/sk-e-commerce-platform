package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rabbitmq.client.Connection;


@SpringBootApplication
public class ECommercePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECommercePlatformApplication.class, args);
    }
}

