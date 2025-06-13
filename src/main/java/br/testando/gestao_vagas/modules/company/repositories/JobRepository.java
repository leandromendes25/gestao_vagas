package br.testando.gestao_vagas.modules.company.repositories;

import br.testando.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
//select * from job where description like (procure em qualquer lugar)
    List<JobEntity> findByDescriptionContaining(String filter);
}
