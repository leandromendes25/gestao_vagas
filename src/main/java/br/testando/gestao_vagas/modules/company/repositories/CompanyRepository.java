package br.testando.gestao_vagas.modules.company.repositories;

import br.testando.gestao_vagas.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUserNameOrEmail(String userName, String email);
    Optional<CompanyEntity> findByUserName(String userName);


}
