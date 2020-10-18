package com.cs322.ors.security.filters;

import com.auth0.jwt.JWT;

import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.User;
import com.cs322.ors.security.JwtProperties;
import com.cs322.ors.security.UserPrincipal;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get JWT from header
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        // Delegate to chain filter if no JWT
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // If JWT exist, authenticate the user
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX,"");

        if (token != null) {
            // Validate JWT and get the user
            String username = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();

           if (username != null) {
                User user = userRepository.findByUsername(username);
                UserPrincipal principal = new UserPrincipal(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
