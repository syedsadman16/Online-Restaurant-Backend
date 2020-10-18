package com.cs322.ors.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.cs322.ors.security.Credentials;
import com.cs322.ors.security.JwtProperties;
import com.cs322.ors.security.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	//Authenticate credentials of POST /api/auth
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		//Map the credentials given 
		Credentials credentials = null;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("");
		}

		//Create authentication token
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), credentials.getPassword(), new ArrayList<>());

		// Authenticate user
		Authentication auth = authenticationManager.authenticate(authenticationToken);

		return auth;
	}

	// POST /api/auth success
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// Get the principal
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

		// Create JWT
		String token = JWT.create().withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.sign(HMAC512(JwtProperties.SECRET.getBytes()));

		// Respond with JWT
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
	}

}
