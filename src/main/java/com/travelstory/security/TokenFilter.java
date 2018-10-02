package com.travelstory.security;

import com.travelstory.entity.UserRole;
import com.travelstory.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = tokenProvider.resolveAccessToken(request);
        String refreshToken = tokenProvider.resolveRefreshToken(request);

        try {
            if (accessToken != null && tokenProvider.validateToken(accessToken.substring(7))) { // TODO заміити на пропе
                Authentication auth = tokenProvider.getAuthentication(accessToken.substring(7));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else if (refreshToken != null && tokenProvider.validateToken(refreshToken)
                    && !tokenProvider.validateToken(accessToken)) {
                // checkExpiration(accessToken)
                String userName = tokenProvider.getEmail(refreshToken);
                /// removeAlreadyFiltredAttributes
                UserRole userRole = userRepository.findByEmail(userName).getUserRole();
                Long id = userRepository.findByEmail(userName).getId();
                response.setHeader("Access-token", tokenProvider.createAccessToken(userName, userRole, id));
                response.setHeader("Refresh-token", tokenProvider.createRefreshToken(userName));
            }
        } catch (RuntimeException e) {
            log.error("Exception in token filter");
        }
        filterChain.doFilter(request, response);
    }

}
