package com.example.flowcalculator.security;

import com.example.flowcalculator.data.model.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    @Value("${security.constants.jwt-exp-time}")
    private Long jwtExpTime;

    @Value("${security.constants.jwt-secret-key}")
    private String jwtSecretKey;

    public String generateToken(AppUser user){
        String username = user.getUsername();
        return generateToken(username);
    }
    public String generateToken(Authentication authentication) {
        AuthenticationUser authenticatedUser = (AuthenticationUser) authentication.getPrincipal();
        String username = authenticatedUser.getUsername();
        return generateToken(username);
    }
    public String generateToken(String username) {
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey)
                    .build()
                    .parseClaimsJws(token);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthenticationCredentialsNotFoundException("Invalid token");
        }
    }
}
