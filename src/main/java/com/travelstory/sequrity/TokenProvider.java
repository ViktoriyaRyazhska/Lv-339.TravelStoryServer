package com.travelstory.sequrity;

import com.travelstory.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("SecretKey")
    private String secretKey;

    @Value("259200000")
    private long validityInMilliseconds;

    public TokenProvider() {
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email, UserRole role, Long id) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        claims.put("id", id);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
}
