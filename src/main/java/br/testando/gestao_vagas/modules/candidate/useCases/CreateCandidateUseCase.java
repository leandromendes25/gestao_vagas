package br.testando.gestao_vagas.modules.candidate.useCases;

import br.testando.gestao_vagas.exceptions.UserFoundException;
import br.testando.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.testando.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity entity){
        repository.findByUserNameOrEmail(entity.getUserName(),entity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException("Candidate Already registered");
        });
        var password = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(password);
        return repository.save(entity);
    }
}
