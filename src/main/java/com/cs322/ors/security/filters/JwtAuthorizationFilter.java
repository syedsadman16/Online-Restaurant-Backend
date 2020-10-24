package com.cs322.ors.security.filters;

import com.auth0.jwt.JWT;

import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.User;
import com.cs322.ors.security.JwtProperties;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.security.UserPrincipalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private UserPrincipalService userPrincipalService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
			UserPrincipalService userPrincipalService) {
		super(authenticationManager);
		this.userPrincipalService = userPrincipalService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Get JWT from header
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}

	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

		if (token != null) {
			// Validate JWT and get the user
			String username = JWT.require(HMAC512(JwtProperties.SECRET.getBytes())).build().verify(token).getSubject();

			UserDetails userDetails = userPrincipalService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

		}
		return null;
	}
}
