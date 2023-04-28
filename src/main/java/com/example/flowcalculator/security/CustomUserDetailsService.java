package com.example.flowcalculator.security;

import com.example.flowcalculator.data.model.AppUser;
import com.example.flowcalculator.data.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user =
                (AppUser) repository.findrByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return AuthenticationUser.builder()
                .appUser(user)
                .roles(List.of("ROLE_USER"))
                .build();
    }
}
