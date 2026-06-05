package com.example.projeto_disciplinas.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity 
@Table(name = "curso") 
public class Curso {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sigla", nullable = false, length = 10)
    private String sigla;

    @Column(name = "descr_curso", nullable = false) 
    private String descricao;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "status")
    private String status; 

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<MatrizCurricular> matrizes;

    public void setDataCadastro(LocalDate now) {

        throw new UnsupportedOperationException("Unimplemented method 'setDataCadastro'");
    }

    public void setStatus(String string) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }

    public Long getId() {
    
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getStatus() {
        return status;
    }

    public List<MatrizCurricular> getMatrizes() {
        return matrizes;
    }

    public void setMatrizes(List<MatrizCurricular> matrizes) {
        this.matrizes = matrizes;
    }

    public void setNome(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNome'");
    }

    public void setCargaHoraria(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCargaHoraria'");
    }

    public void setCodigo(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCodigo'");
    }

    

}