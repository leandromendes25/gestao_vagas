package br.testando.gestao_vagas.modules.candidate.controllers;

import br.testando.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.testando.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.testando.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.testando.gestao_vagas.modules.company.entities.JobEntity;
import br.testando.gestao_vagas.modules.company.useCases.ListAllJobsUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

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

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

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
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<JobEntity> getMethodName(@RequestParam String filter) {
        return listAllJobsUseCase.execute(filter);
    }

    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id");
        try{
        var profile = profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
        return ResponseEntity.ok().body(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
