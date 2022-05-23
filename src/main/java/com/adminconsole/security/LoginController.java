package com.adminconsole.security;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LoginController {

    private final UsernamePasswordAuthenticationService usernamePasswordAuthenticationService;

    LoginController(UsernamePasswordAuthenticationService usernamePasswordAuthenticationService) {
        this.usernamePasswordAuthenticationService = usernamePasswordAuthenticationService;
    }

    @PostMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) {
        return ResponseEntity.ok(
                usernamePasswordAuthenticationService.login(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())
        );
    }

}
