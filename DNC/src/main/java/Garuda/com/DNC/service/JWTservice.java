package Garuda.com.DNC.service;

import Garuda.com.DNC.Model.LocalUser;
import Garuda.com.DNC.Model.Vendor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JWTservice {
    @Value("${jwt.algorithm.key}")
    private String algorithmkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    private static final String USERNAME_KEY="USERNAME";

    @PostConstruct
    public void postconstruct(){
        algorithm=Algorithm.HMAC256(algorithmkey);
    }

    public String jwtgenerator(LocalUser user){
        return JWT.create()
                .withClaim(USERNAME_KEY, user.getUser())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);


    }
    public String jwtgenerator2(Vendor vend){
        return JWT.create()
                .withClaim(USERNAME_KEY, vend.getUser())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);


    }
public String getUsername(String token){
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
}
   }
