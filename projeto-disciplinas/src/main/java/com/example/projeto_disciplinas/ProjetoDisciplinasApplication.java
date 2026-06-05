package com.example.projeto_disciplinas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// Essa linha avisa ao Spring para NÃO tentar se conectar ao banco ao iniciar:
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProjetoDisciplinasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoDisciplinasApplication.class, args);
    }
}
