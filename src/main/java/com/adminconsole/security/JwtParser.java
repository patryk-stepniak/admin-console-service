package com.adminconsole.security;

import io.jsonwebtoken.*;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import java.util.Date;
import java.util.Map;

class JwtParser {

    private final String secret;

    JwtParser(String secret) {
        this.secret = secret;
    }

    String getToken(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(Map.of(Claims.SUBJECT, claims))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000000 * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    Jwt validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret)
                    .parse(token);
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException exception) {
            throw new OAuth2AuthenticationException(exception.getMessage());
        }
    }

}
