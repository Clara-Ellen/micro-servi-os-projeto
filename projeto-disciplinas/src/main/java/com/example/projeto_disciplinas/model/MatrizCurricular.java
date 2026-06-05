package com.example.projeto_disciplinas.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matriz_curricular")
public class MatrizCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "status")
    private String status;

    // --- O RELACIONAMENTO (Chave Estrangeira / FK) ---
    @ManyToOne // Muitas matrizes para um único Curso
    @JoinColumn(name = "fk_curso_id", nullable = false) // Nome da coluna FK que está na tabela 'matriz_curricular' no seu diagrama
    private Curso curso;

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}