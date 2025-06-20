package br.testando.gestao_vagas.modules.company.controllers;

import br.testando.gestao_vagas.modules.company.dto.CreateJobDto;
import br.testando.gestao_vagas.modules.company.entities.JobEntity;
import br.testando.gestao_vagas.modules.company.useCases.CreateJobsUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobsUseCase createJobsUseCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");// Pode ser objeto e o setId só aceita UUID
       var job = JobEntity.builder().benefits(createJobDto.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDto.getDescription())
                .level(createJobDto.getLevel()).build();
        return createJobsUseCase.execute(job);
    }
}
