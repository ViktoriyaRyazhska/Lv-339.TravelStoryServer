package com.travelstory.security;

import com.travelstory.entity.UserRole;
import com.travelstory.services.UserDetailServiceImplementation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("SecretKey")
    private String secretKey;

    @Value("259200000")
    private long validityInMilliseconds;

    private final UserDetailServiceImplementation userDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(String email, UserRole role, Long id) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        claims.put("id", id);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public String createRefreshToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetail = userDetails.loadUserByUsername(getEmail(token));
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetail, "", userDetail.getAuthorities());
        return auth;

    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Problem with validating token");
            return false;
        }
    }

    public String resolveAccessToken(HttpServletRequest req) {
        String accessToken = req.getHeader("Access-token");
        return accessToken;
    }

    public String resolveRefreshToken(HttpServletRequest req) {
        String accessToken = req.getHeader("Refresh-token");
        return accessToken;
    }

}
