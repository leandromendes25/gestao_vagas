package br.testando.gestao_vagas.modules.candidate.controllers;

import br.testando.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.testando.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity entity){
    try{
    var result = createCandidateUseCase.execute(entity);
    return ResponseEntity.ok().body(result);
    }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
}
