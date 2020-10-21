package com.cs322.ors.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cs322.ors.db.UserRepository;
import com.cs322.ors.security.filters.JwtAuthenticationFilter;
import com.cs322.ors.security.filters.JwtAuthorizationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private UserPrincipalService userPrincipalService;

	public SecurityConfiguration(UserPrincipalService userPrincipalService) {
		this.userPrincipalService = userPrincipalService;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	final JwtAuthenticationFilter AuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
    	AuthenticationFilter.setFilterProcessesUrl("/api/auth");
   	  	final JwtAuthorizationFilter AuthorizationFilter = new JwtAuthorizationFilter(authenticationManager(), userPrincipalService);
   	 
    	http
    	.csrf().disable()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
    	.headers().frameOptions().sameOrigin() //For DB GUI
    	.and()
    	.addFilter(AuthenticationFilter)
    	.addFilter(AuthorizationFilter)
    	.authorizeRequests()
    	.mvcMatchers("/h2-console/**").permitAll(); //For Db GUI

    }

    @Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userPrincipalService);
		return daoAuthenticationProvider;
	}
    
  
}