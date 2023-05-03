package com.frt.authservice.service.impl.user;

import com.frt.authservice.persistence.entity.FrtUser;
import com.frt.authservice.persistence.repository.FrtUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final FrtUserRepository frtUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        FrtUser frtUser = frtUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(frtUser.getEmail(),
                frtUser.getPassword(),
                frtUser.getAuthorities());
    }
}
