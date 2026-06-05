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

@Service // 1. Avisa ao Spring que esta classe guarda as regras de negócio
public class CursoMatrizService {

    @Autowired // 2. Traz o Repository do Curso sem usar "new"
    private CursoRepository cursoRepository;

    @Autowired // 3. Traw o Repository da Matriz Curricular
    private MatrizCurricularRepository matrizCurricularRepository;

    // --- CADASTRO DE CURSO (Apenas ADMINISTRADOR) ---
    @Transactional
    public Curso cadastrarCurso(Curso curso, String perfilUsuario) {
        // Validação de acesso do coordenador/adm
        if (!"ADMINISTRADOR".equalsIgnoreCase(perfilUsuario)) {
            throw new RuntimeException("Acesso negado: Apenas administradores podem cadastrar cursos.");
        }

        curso.setDataCadastro(LocalDate.now());
        curso.setStatus("ATIVO");
        
        return cursoRepository.save(curso);
    }

    // --- CADASTRO DE MATRIZ CURRICULAR (Aplica a RN005) ---
    @Transactional
    public MatrizCurricular cadastrarMatrizCurricular(MatrizCurricular novaMatriz, String perfilUsuario) {
        // Validação de acesso do coordenador/adm
        if (!"ADMINISTRADOR".equalsIgnoreCase(perfilUsuario)) {
            throw new RuntimeException("Acesso negado: Apenas administradores podem cadastrar matrizes.");
        }

        if (novaMatriz.getCurso() == null || novaMatriz.getCurso().getId() == null) {
            throw new RuntimeException("Uma matriz deve estar vinculada a um curso válido.");
        }

        Long cursoId = novaMatriz.getCurso().getId();

        // Aplicação da RN005: Apenas uma matriz ativa por curso
        if ("ATIVO".equals(novaMatriz.getStatus())) {
            // Busca no banco se já existe alguma matriz ativa para este curso
            List<MatrizCurricular> matrizesAtivas = matrizCurricularRepository
                    .findByCursoIdAndStatus(cursoId, "ATIVO");

            // Se achar alguma, muda o status para INATIVO antes de salvar a nova
            for (MatrizCurricular matrizAtiva : matrizesAtivas) {
                matrizAtiva.setStatus("INATIVO");
                matrizCurricularRepository.save(matrizAtiva);
            }
        }

        novaMatriz.setDataCadastro(LocalDate.now());
        return matrizCurricularRepository.save(novaMatriz);
    }
}
