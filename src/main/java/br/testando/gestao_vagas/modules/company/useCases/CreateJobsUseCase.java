package br.testando.gestao_vagas.modules.company.useCases;

import br.testando.gestao_vagas.modules.company.entities.JobEntites;
import br.testando.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobsUseCase {
    @Autowired
    private JobRepository repository;


    public JobEntites execute(JobEntites jobEntites){
    return repository.save(jobEntites);
    }
}
