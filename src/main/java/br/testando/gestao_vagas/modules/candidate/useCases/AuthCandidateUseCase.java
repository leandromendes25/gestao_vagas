package br.testando.gestao_vagas.modules.candidate.useCases;

import br.testando.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.testando.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.testando.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = candidateRepository.findByUserName(authCandidateRequestDTO.userName()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });
        var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        var experiesIn = Instant.now().plus(Duration.ofHours(2));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas").
                withSubject(candidate.getId().toString())
                //roles -> reindicações que o candidato poderá ter
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(experiesIn).sign(algorithm);

        return new AuthCandidateResponseDTO(token, experiesIn.toEpochMilli());
    }

}
