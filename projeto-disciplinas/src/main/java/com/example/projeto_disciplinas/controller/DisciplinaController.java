package com.example.projeto_disciplinas.controller;

import com.example.projeto_disciplinas.model.Disciplina;
import com.example.projeto_disciplinas.servico.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gateway/disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    // Rota para Cadastrar Disciplina
    @PostMapping
    public ResponseEntity<?> cadastrarDisciplina(
            @RequestBody Disciplina disciplina,
            @RequestHeader("Perfil-Usuario") String perfilUsuario) {
        try {
            Disciplina novaDisciplina = disciplinaService.cadastrarDisciplina(disciplina, perfilUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaDisciplina);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Rota para Adicionar um Pré-requisito a uma Disciplina existente
    @PostMapping("/{id}/pre-requisitos/{idPreRequisito}")
    public ResponseEntity<?> adicionarPreRequisito(
            @PathVariable Long id,
            @PathVariable Long idPreRequisito,
            @RequestHeader("Perfil-Usuario") String perfilUsuario) {
        try {
            Disciplina disciplinaAtualizada = disciplinaService.adicionarPreRequisito(id, idPreRequisito, perfilUsuario);
            return ResponseEntity.ok(disciplinaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
