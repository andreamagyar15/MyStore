package ssit.java0.springMVC.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTToken {
    private String token;
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public JWTToken(String subject) {

        this.token =  Jwts.builder().setSubject(subject).signWith(key).compact();

    }

    public String getToken() {
        return token;
    }


}
