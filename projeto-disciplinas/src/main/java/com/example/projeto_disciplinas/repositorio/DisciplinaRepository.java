package com.example.projeto_disciplinas.repositorio;

import com.example.projeto_disciplinas.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}
    

