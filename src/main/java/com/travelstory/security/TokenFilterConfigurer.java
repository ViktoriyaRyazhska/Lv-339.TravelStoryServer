 package com.travelstory.security;

 import com.travelstory.repositories.UserRepository;
 import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.web.DefaultSecurityFilterChain;
 import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

 public class TokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
 private TokenProvider tokenProvider;

 private UserRepository userRepository;

 public TokenFilterConfigurer(TokenProvider tokenProvider, UserRepository userRepository) {
 this.tokenProvider = tokenProvider;

 this.userRepository = userRepository;
 }

 @Override
 public void configure(HttpSecurity http) throws Exception {
 http.addFilterAfter(new TokenFilter(tokenProvider, userRepository), BasicAuthenticationFilter.class);
 }
 }
