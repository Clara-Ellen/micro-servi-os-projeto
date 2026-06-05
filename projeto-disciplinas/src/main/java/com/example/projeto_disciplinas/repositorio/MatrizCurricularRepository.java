package com.example.projeto_disciplinas.repositorio;

import com.example.projeto_disciplinas.model.MatrizCurricular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatrizCurricularRepository extends JpaRepository<MatrizCurricular, Long> {

    List<MatrizCurricular> findByCursoIdAndStatus(Long cursoId, String status);
}
