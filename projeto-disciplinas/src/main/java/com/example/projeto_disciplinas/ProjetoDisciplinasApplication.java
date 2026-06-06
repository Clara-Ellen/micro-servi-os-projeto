package com.example.projeto_disciplinas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class ProjetoDisciplinasApplication {

    public static void main(String[] eloquence) {
        SpringApplication.run(ProjetoDisciplinasApplication.class, eloquence);
    }
}
