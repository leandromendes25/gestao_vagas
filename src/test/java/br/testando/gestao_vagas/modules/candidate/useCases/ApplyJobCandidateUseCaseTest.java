package br.testando.gestao_vagas.modules.candidate.useCases;

import br.testando.gestao_vagas.exceptions.JobNotFoundException;
import br.testando.gestao_vagas.exceptions.UserNotFoundException;
import br.testando.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.testando.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.testando.gestao_vagas.modules.company.repositories.JobRepository;
import br.testando.gestao_vagas.modules.company.useCases.ApplyJobCandidateUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private JobRepository jobRepository;
 

    @Test
    @DisplayName("should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound(){
    try{
        applyJobCandidateUseCase.execute(null, null);
    }catch(Exception e){
        //Seja a instancia da classe
        assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
    }
    @Test
    public void shouldNotBeAbleToApplyJobWithJobNotFound(){
        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);
        //thenReturn só aceita optional, então convertemos a entidade para optional
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try{
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}
