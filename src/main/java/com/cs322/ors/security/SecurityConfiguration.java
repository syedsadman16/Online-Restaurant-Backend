package com.cs322.ors.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cs322.ors.db.UserRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private UserPrincipalService userPrincipalService;
	private UserRepository userRepository;

	public SecurityConfiguration(UserPrincipalService userPrincipalService, UserRepository userRepository) {
		this.userPrincipalService = userPrincipalService;
		this.userRepository = userRepository;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	http
    	.csrf().disable()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
    	.headers().frameOptions().sameOrigin() //For DB GUI
    	.and()
    	.authorizeRequests()
        .antMatchers("/manager").hasRole("MANAGER")
        .antMatchers("/user").hasAnyRole("MANAGER", "USER")
        .antMatchers("/").permitAll()
        .and().formLogin();
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