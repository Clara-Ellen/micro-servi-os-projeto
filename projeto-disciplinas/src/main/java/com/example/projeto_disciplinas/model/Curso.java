package com.example.projeto_disciplinas.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity // 1. Diz ao Spring que essa classe é uma tabela no banco de dados
@Table(name = "curso") // 2. Define o nome exato da tabela no banco
public class Curso {

    @Id // 3. Diz que este atributo é a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. Diz que o ID é auto-incremento (serial)
    @Column(name = "id")
    private Long id;

    @Column(name = "sigla", nullable = false, length = 10) // 5. Mapeia a coluna varchar
    private String sigla;

    @Column(name = "descr_curso", nullable = false) // 'descricao' no diagrama
    private String descricao;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "status")
    private String status; // No diagrama está como tipo_status (pode usar String aqui para simplificar no 3º semestre)

    // Relacionamento de 1 para Muitos com Matriz (Um curso tem várias matrizes)
    // O 'mappedBy' diz que o mapeamento forte está na classe MatrizCurricular no atributo 'curso'
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<MatrizCurricular> matrizes;

    // --- GETTERS E SETTERS ---
    // (Você precisa gerar os Getters e Setters de todos os campos aqui embaixo)
    // Dica no VS Code: Alt + Shift + O (ou clique com o botão direito -> Source Action -> Generate Getters and Setters)
}