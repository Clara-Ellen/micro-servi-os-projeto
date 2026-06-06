package com.example.projeto_disciplinas.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Column(name = "status")
    private String status; 

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    
    @ManyToMany
    @JoinTable(
        name = "curso_disciplina",
        joinColumns = @JoinColumn(name = "fk_disciplina_id"),
        inverseJoinColumns = @JoinColumn(name = "fk_curso_id")
    )
    private List<Curso> cursos = new ArrayList<>();

    
    @ManyToMany
    @JoinTable(
        name = "pre_requisito",
        joinColumns = @JoinColumn(name = "fk_disciplina_id"),        
        inverseJoinColumns = @JoinColumn(name = "fk_disciplina_prereq")
    )
    private List<Disciplina> preRequisitos = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }


    public List<Curso> getCursos() { return cursos; }
    public void setCursos(List<Curso> cursos) { this.cursos = cursos; }

    public List<Disciplina> getPreRequisitos() { return preRequisitos; }
    public void setPreRequisitos(List<Disciplina> preRequisitos) { this.preRequisitos = preRequisitos; }
    public void setCodigo(String string) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setCodigo'");
    }
}