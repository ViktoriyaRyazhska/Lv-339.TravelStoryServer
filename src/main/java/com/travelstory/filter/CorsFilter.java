package com.travelstory.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.travelstory.config.PropertiesConfig.getPropertyValue;

@Component
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", getPropertyValue("Access-Control-Allow-Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", getPropertyValue("Access-Control-Allow-Methods"));
        httpServletResponse.setHeader("Access-Control-Allow-Headers", getPropertyValue("Access-Control-Allow-Headers"));
        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                getPropertyValue("Access-Control-Expose-Headers"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                getPropertyValue("Access-Control-Allow-Credentials"));

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
    }
}
