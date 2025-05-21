package br.testando.gestao_vagas.modules.company.useCases;

import br.testando.gestao_vagas.exceptions.UserFoundException;
import br.testando.gestao_vagas.modules.company.entities.CompanyEntity;
import br.testando.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){

        repository.findByUserNameOrEmail(companyEntity.getUserName(), companyEntity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException("User not found");
        });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        return repository.save(companyEntity);
    }
}