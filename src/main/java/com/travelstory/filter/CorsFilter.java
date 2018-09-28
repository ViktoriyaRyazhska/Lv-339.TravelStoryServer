package com.travelstory.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept,"
                + " X-Requested-With, Content-Type, Authorization,Access-Control-Request-Method, Access-Control-Request-Headers");
        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
    }
}
