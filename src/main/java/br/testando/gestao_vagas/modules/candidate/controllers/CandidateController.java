package br.testando.gestao_vagas.modules.candidate.controllers;

import br.testando.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.testando.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.testando.gestao_vagas.modules.company.entities.JobEntity;
import br.testando.gestao_vagas.modules.company.useCases.ListAllJobsUseCase;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ListAllJobsUseCase listAllJobsUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity entity){
    try{
    var result = createCandidateUseCase.execute(entity);
    return ResponseEntity.ok().body(result);
    }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")//indica que tem que estar autorizado
    public List<JobEntity> getMethodName(@RequestParam String filter) {
        return listAllJobsUseCase.execute(filter);
    }
    
}
