package br.testando.gestao_vagas.modules.company.controllers;

import br.testando.gestao_vagas.modules.company.entities.JobEntites;
import br.testando.gestao_vagas.modules.company.useCases.CreateJobsUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobsUseCase createJobsUseCase;
    @PostMapping("/")
    public JobEntites create(@Valid @RequestBody JobEntites jobEntites){
            return createJobsUseCase.execute(jobEntites);
    }
}
