package br.testando.gestao_vagas.modules.candidate.useCases;

import br.testando.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.testando.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        var candidateDTO = ProfileCandidateResponseDTO
                .builder().description(candidate.getDescription())
                .userName(candidate.getUserName())
                .id(candidate.getId()).email(candidate.getEmail()).name(candidate.getName()).build();
    return candidateDTO;
    }

}
