package br.testando.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {//necessário remover Bearer do token
    @Value("${security.token.secret}")
    private String secretKey;
    public String validateToken(String token){
    token = token.replace("Bearer ","");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        //dentro do require precisa passar o algortimo
        try{
       var subject = JWT.require(algorithm).build().verify(token)
                .getSubject();//id do usuario
        return subject;
        }catch (JWTVerificationException ex){
            ex.printStackTrace();
            return "";
        }
    }
}
