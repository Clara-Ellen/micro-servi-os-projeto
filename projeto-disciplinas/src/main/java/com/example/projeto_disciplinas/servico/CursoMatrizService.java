package com.example.projeto_disciplinas.servico;

import com.example.projeto_disciplinas.model.Curso;
import com.example.projeto_disciplinas.model.MatrizCurricular;
import com.example.projeto_disciplinas.repositorio.CursoRepository;
import com.example.projeto_disciplinas.repositorio.MatrizCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service 
public class CursoMatrizService {

    @Autowired 
    private CursoRepository cursoRepository;

    @Autowired 
    private MatrizCurricularRepository matrizCurricularRepository;

    
    @Transactional
    public Curso cadastrarCurso(Curso curso, String perfilUsuario) {
        
        if (!"ADMINISTRADOR".equalsIgnoreCase(perfilUsuario)) {
            throw new RuntimeException("Acesso negado: Apenas administradores podem cadastrar cursos.");
        }

        curso.setDataCadastro(LocalDate.now());
        curso.setStatus("ATIVO");
        
        return cursoRepository.save(curso);
    }

    @Transactional
    public MatrizCurricular cadastrarMatrizCurricular(MatrizCurricular novaMatriz, String perfilUsuario) {
        if (!"ADMINISTRADOR".equalsIgnoreCase(perfilUsuario)) {
            throw new RuntimeException("Acesso negado: Apenas administradores podem cadastrar matrizes.");
        }

        if (novaMatriz.getCurso() == null || novaMatriz.getCurso().getId() == null) {
            throw new RuntimeException("Uma matriz deve estar vinculada a um curso válido.");
        }

        Long cursoId = novaMatriz.getCurso().getId();

        
        if ("ATIVO".equals(novaMatriz.getStatus())) {
        
            List<MatrizCurricular> matrizesAtivas = matrizCurricularRepository
                    .findByCursoIdAndStatus(cursoId, "ATIVO");

            
            for (MatrizCurricular matrizAtiva : matrizesAtivas) {
                matrizAtiva.setStatus("INATIVO");
                matrizCurricularRepository.save(matrizAtiva);
            }
        }

        novaMatriz.setDataCadastro(LocalDate.now());
        return matrizCurricularRepository.save(novaMatriz);
    }
}
