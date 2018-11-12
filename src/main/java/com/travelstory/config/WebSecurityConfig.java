package com.travelstory.config;

import com.travelstory.repositories.UserRepository;
import com.travelstory.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProviderokenProvider;
    private final UserRepository userRepository;

    @Qualifier("userDetailServiceImplementation")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable();
        //
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //
        // http.authorizeRequests().antMatchers("http://localhost:8080/user/**").permitAll().antMatchers(HttpMethod.OPTIONS,
        // "/**").permitAll().anyRequest().authenticated();
        //
        // http.apply(new TokenFilterConfigurer(tokenProviderokenProvider, userDAO));

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        // http.authorizeRequests()
        // .antMatchers("/admin/**").access("hasRole('ADMIN')").antMatchers(HttpMethod.OPTIONS,
        // "/**").permitAll().anyRequest().authenticated();
        // http.authorizeRequests().antMatchers("/**").permitAll().antMatchers(HttpMethod.OPTIONS,
        // "/**").permitAll().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
