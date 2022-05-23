package com.adminconsole.security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;

class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final String CLAIM_VALUE_SEPARATOR = "=";
    private static final int CLAIM_VALUE_INDEX = 1;
    private static final String JSON_SPECIAL_CHARS = "}";
    private final JwtParser jwtParser;

    private final UserDetailsService userDetailsService;

    JwtAuthenticationProvider(String jwtSecret, UserDetailsService userDetailsService) {
        this.jwtParser = new JwtParser(jwtSecret);
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        final Jwt jwt = jwtParser.validateToken(((BearerTokenAuthenticationToken) authentication).getToken());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                extractSubjectValue((DefaultClaims) jwt.getBody())
        );
        if (!userDetails.isEnabled()) {
            throw new OAuth2AuthenticationException("The user is not activated.");
        }
        return authentication;
    }

    private String extractSubjectValue(DefaultClaims claims) {
        final String subject = claims.getSubject();
        return subject != null && subject.contains(CLAIM_VALUE_SEPARATOR) ?
                subject.split(CLAIM_VALUE_SEPARATOR)[CLAIM_VALUE_INDEX].replaceAll(JSON_SPECIAL_CHARS, "") : null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerTokenAuthenticationToken.class.equals(authentication);
    }

}
