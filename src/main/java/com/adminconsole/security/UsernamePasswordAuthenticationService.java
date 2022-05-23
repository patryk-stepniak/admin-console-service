package com.adminconsole.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

class UsernamePasswordAuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtParser jwtParser;

    UsernamePasswordAuthenticationService(UserDetailsService userDetailsService,
                                          AuthenticationManager authenticationManager,
                                          String jwtSecret) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtParser = new JwtParser(jwtSecret);
    }

    String login(String username, String password) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return jwtParser.getToken(
                Map.of(Claims.SUBJECT, userDetailsService.loadUserByUsername(username).getUsername())
        );
    }

}
