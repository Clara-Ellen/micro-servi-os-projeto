package com.example.projeto_disciplinas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

// Imports ajustados perfeitamente para o seu pacote real
import com.example.projeto_disciplinas.model.Curso;
import com.example.projeto_disciplinas.model.Disciplina;
import com.example.projeto_disciplinas.repositorio.CursoRepository;
import com.example.projeto_disciplinas.repositorio.DisciplinaRepository;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class ProjetoDisciplinasApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ProjetoDisciplinasApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CursoRepository cursoRepository, DisciplinaRepository disciplinaRepository) {
        return (args) -> {
            System.out.println("--------------------------------------------");
            System.out.println("INICIANDO INSERÇÃO DE DADOS DE TESTE...");
            System.out.println("--------------------------------------------");

            // 1. Criando e salvando um Curso de Exemplo
            Curso cursoAnl = new Curso();
            cursoAnl.setNome("Análise e Desenvolvimento de Sistemas");
            cursoAnl.setCargaHoraria(2000);
            cursoAnl.setCodigo("ADS");
            
            cursoAnl = cursoRepository.save(cursoAnl);
            System.out.println("Curso salvo com sucesso! ID: " + cursoAnl.getId());

            // 2. Criando e salvando Disciplinas de Exemplo
            Disciplina disc1 = new Disciplina();
            disc1.setNome("Algoritmos e Lógica de Programação");
            disc1.setCargaHoraria(80);
            disc1.setCodigo("ALG1");
            disciplinaRepository.save(disc1);

            Disciplina disc2 = new Disciplina();
            disc2.setNome("Banco de Dados Relacional");
            disc2.setCargaHoraria(80);
            disc2.setCodigo("BD1");
            disciplinaRepository.save(disc2);

            Disciplina disc3 = new Disciplina();
            disc3.setNome("Estrutura de Dados em Java");
            disc3.setCargaHoraria(40);
            disc3.setCodigo("EDJ");
            disciplinaRepository.save(disc3);

            System.out.println("Disciplinas salvas com sucesso!");
            System.out.println("--------------------------------------------");
            System.out.println("TESTE CONCLUÍDO. VERIFIQUE O SUPABASE!");
            System.out.println("--------------------------------------------");
        };
    }
}
