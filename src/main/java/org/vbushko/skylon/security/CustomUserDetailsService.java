package org.vbushko.skylon.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vbushko.skylon.user.entity.User;
import org.vbushko.skylon.user.service.UserService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByLogin(username);
        return CustomUserDetails.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .grantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("USER")))
                .build();
    }
}
