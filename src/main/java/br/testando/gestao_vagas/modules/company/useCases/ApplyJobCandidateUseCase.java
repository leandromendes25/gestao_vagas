package br.testando.gestao_vagas.modules.company.useCases;

import br.testando.gestao_vagas.exceptions.JobNotFoundException;
import br.testando.gestao_vagas.exceptions.UserNotFoundException;
import br.testando.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.testando.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {
    //ID candidato
    //ID vaga
    @Autowired
    private CandidateRepository candidateRepository;
    private JobRepository jobRepository;
    public void execute(UUID idCandidate, UUID idJob){
    candidateRepository.findById(idCandidate).orElseThrow(() -> {
        throw new UserNotFoundException();
    });jobRepository.findById(idJob).orElseThrow(() -> {
        throw new JobNotFoundException();
    });
    }
}
