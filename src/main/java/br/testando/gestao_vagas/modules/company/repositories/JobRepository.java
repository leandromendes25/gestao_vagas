package br.testando.gestao_vagas.modules.company.repositories;

import br.testando.gestao_vagas.modules.company.entities.JobEntites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntites, UUID> {
}
