package com.example.projeto_disciplinas.controller;

import com.example.projeto_disciplinas.model.Curso;
import com.example.projeto_disciplinas.model.MatrizCurricular;
import com.example.projeto_disciplinas.servico.CursoMatrizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController 
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CursoMatrizController {

    @Autowired
    private CursoMatrizService cursoMatrizService;

    
    @PostMapping("/cursos") 
    public ResponseEntity<?> cadastrarCurso(
            @RequestBody Curso curso,
            @RequestHeader("Perfil-Usuario") String perfilUsuario) {
         try {
            Curso novoCurso = cursoMatrizService.cadastrarCurso(curso, perfilUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
        } catch (RuntimeException e) {
            // Se o perfil não for ADMINISTRADOR, cai aqui e devolve erro 403 (Proibido)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Rota final: /api/matrizes
    @PostMapping("/matrizes") // Este já está correto!
    public ResponseEntity<?> cadastrarMatriz(
            @RequestBody MatrizCurricular matriz,
            @RequestHeader("Perfil-Usuario") String perfilUsuario) {
         try {
            MatrizCurricular novaMatriz = cursoMatrizService.cadastrarMatrizCurricular(matriz, perfilUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaMatriz);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
