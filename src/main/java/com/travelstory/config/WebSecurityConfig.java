package com.travelstory.config;

import com.travelstory.dao.UserDAO;
import com.travelstory.security.TokenProvider;
import lombok.RequiredArgsConstructor;
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
    private final TokenProvider tokenProviderokenProvider;
    private final UserDAO userDAO;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // http.csrf().disable();
        //
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //
        // http.authorizeRequests()
        //
        // .antMatchers("/user/**").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest()
        // .authenticated();
        //
        // http.apply(new TokenFilterConfigurer(tokenProviderokenProvider, userDAO));

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
    }

    /*
     * @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(12); }
     */

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
