package br.com.triagemsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.triagemsystem.config.filters.JWTAuthenticationFilter;
import br.com.triagemsystem.config.filters.JWTLoginFilter;
import br.com.triagemsystem.service.AuthenticationService;

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
		.anyRequest().authenticated()
		.and()
		
		.addFilterBefore(new JWTLoginFilter("/auth/login", authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
					
		.addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

}