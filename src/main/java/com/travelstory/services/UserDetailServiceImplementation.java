package com.travelstory.services;

import com.travelstory.entity.User;
import com.travelstory.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        log.info("user found");
        if (user == null) {
            throw new UsernameNotFoundException("UserDTO '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(email).password(user.getPassword())
                .authorities(user.getUserRole()).accountExpired(false).accountLocked(false).credentialsExpired(false)
                .disabled(false).build();
    }
}