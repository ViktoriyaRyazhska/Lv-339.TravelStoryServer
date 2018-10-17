package com.travelstory.config;

import com.travelstory.repositories.UserRepository;
import com.travelstory.security.TokenFilterConfigurer;
import com.travelstory.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final TokenProvider tokenProvider;
    @Autowired
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/api/user/**", "/api/resetProfilePic", "/api/uploadProfilePic")
                .hasAnyAuthority("USER", "ADMIN").antMatchers("/api/login", "/api/registrate").permitAll().anyRequest()
                .authenticated();

        http.apply(new TokenFilterConfigurer(tokenProvider, userRepository));

    }

    /*
     * @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(12); }
     */

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
