package com.travelstory.security;

import com.travelstory.dao.UserDAO;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class TokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;

    private UserDAO userDAO;

    public TokenFilterConfigurer(TokenProvider tokenProvider, UserDAO userDAO) {
        this.tokenProvider = tokenProvider;

        this.userDAO = userDAO;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new TokenFilter(tokenProvider, userDAO), BasicAuthenticationFilter.class);
    }
}
