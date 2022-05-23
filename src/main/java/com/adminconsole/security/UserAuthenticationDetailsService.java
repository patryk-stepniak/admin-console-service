package com.adminconsole.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class UserAuthenticationDetailsService implements UserDetailsService {

    private final UserAuthenticationRepository userRepository;

    UserAuthenticationDetailsService(UserAuthenticationRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User %s could not be found.".formatted(username)));
    }
}
