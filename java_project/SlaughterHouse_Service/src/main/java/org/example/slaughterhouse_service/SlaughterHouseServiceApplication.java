package org.example.slaughterhouse_service;

import org.example.slaughterhouse_service.entities.*;
import org.example.slaughterhouse_service.service.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SlaughterHouseServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SlaughterHouseServiceApplication.class, args);
    }
}
