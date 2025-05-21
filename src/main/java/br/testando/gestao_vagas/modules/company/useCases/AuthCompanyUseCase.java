package br.testando.gestao_vagas.modules.company.useCases;

import br.testando.gestao_vagas.exceptions.UserFoundException;
import br.testando.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.testando.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${security.token.secret}")
    private String secretKey;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = companyRepository.findByUserName(authCompanyDTO.getUserName()).
            orElseThrow(() -> {
                throw new UserFoundException("username/password is incorrect");
            });
    var passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(),company.getPassword());
    if(!passwordMatches){
        throw new AuthenticationException();
    }
    //tipo de algoritmo
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))//tempo para expirar o token
                .withSubject(company.getId().toString()).sign(algorithm);//dono do token, geralmente o id do usuario q geralmente espera String
    return token;
    }
}
