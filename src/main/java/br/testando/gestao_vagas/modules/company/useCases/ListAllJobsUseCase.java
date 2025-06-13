package br.testando.gestao_vagas.modules.company.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.testando.gestao_vagas.modules.company.entities.JobEntity;
import br.testando.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsUseCase {
    
    @Autowired
    private JobRepository repository;

    public List<JobEntity> execute(String filter){
        return repository.findByDescriptionContaining(filter);
    }
}
