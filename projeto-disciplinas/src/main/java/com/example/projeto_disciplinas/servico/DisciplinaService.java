package com.example.projeto_disciplinas.servico;

import com.example.projeto_disciplinas.model.Disciplina;
import com.example.projeto_disciplinas.repositorio.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DisciplinaService {

    @Autowired 
    private DisciplinaRepository  disciplinaRepository;

    
    @Transactional
    public Disciplina cadastrarDisciplina(Disciplina disciplina, String perfilUsuario) {
        if (!"ADMINISTRADOR".equalsIgnoreCase(perfilUsuario)) {
            throw new RuntimeException("Acesso negado: Apenas administradores podem cadastrar disciplinas.");
        }

    
        if (disciplina.getCursos() == null || disciplina.getCursos().isEmpty()) {
            throw new RuntimeException("A disciplina deve estar vinculada a pelo menos um curso.");
        }

        disciplina.setDataCadastro(LocalDate.now());
        disciplina.setStatus("ATIVO");

        return  disciplinaRepository.save(disciplina);
    }

    @Transactional
    public Disciplina adicionarPreRequisito(Long disciplinaId, Long preRequisitoId, String perfilUsuario) {
        if (!"ADMINISTRADOR".equalsIgnoreCase(perfilUsuario)) {
            throw new RuntimeException("Acesso negado: Apenas administradores podem gerenciar pré-requisitos.");
        }

        Disciplina disciplina =  disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina principal não encontrada."));

        Disciplina preRequisito =  disciplinaRepository.findById(preRequisitoId)
                .orElseThrow(() -> new RuntimeException("Disciplina de pré-requisito não encontrada."));

        
        if (disciplinaId.equals(preRequisitoId)) {
            throw new RuntimeException("Uma disciplina não pode ser pré-requisito de si mesma.");
        }

    
        disciplina.getPreRequisitos().add(preRequisito);

        return  disciplinaRepository.save(disciplina);
    }
}
