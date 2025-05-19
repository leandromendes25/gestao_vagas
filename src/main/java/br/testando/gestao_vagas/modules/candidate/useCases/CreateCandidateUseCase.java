package br.testando.gestao_vagas.modules.candidate.useCases;

import br.testando.gestao_vagas.exceptions.UserFoundException;
import br.testando.gestao_vagas.modules.candidate.CandidateRepository;
import br.testando.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository repository;

    public CandidateEntity execute(CandidateEntity entity){
        repository.findByUserNameOrEmail(entity.getUserName(),entity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException();
        });
        return repository.save(entity);
    }
}
