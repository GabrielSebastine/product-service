package com.kudi_test.product_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Order(SecurityProperties.DEFAULT_FILTER_ORDER - 5)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


//	@Bean(name = "myAuthenticationManager")
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/","/swagger-ui**", "/swagger-ui.html#/**")
				.permitAll()
			.and()
				.authorizeRequests()
				.anyRequest()
				.fullyAuthenticated()
			.and().csrf().disable()
			.cors()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().httpBasic().authenticationEntryPoint(myBasicAuthenticationEntryPoint());
	}
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.inMemoryAuthentication()
				.withUser("test-user")
				.password("password")
				.roles("USER").and().passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers(	"/swagger-ui.html", "/swagger-ui.html/**", "/v2/api-docs", "/webjars/**",
				"/swagger-resources/**", "/configuration/**", "/api-docs/**"  );
	}

	@Bean
	public AuthenticationEntryPoint myBasicAuthenticationEntryPoint(){
		BasicAuthenticationEntryPoint basicAuthenticationEntryPoint= new BasicAuthenticationEntryPoint(){

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
								 AuthenticationException authException) throws IOException {
				response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
			}
		};
		basicAuthenticationEntryPoint.setRealmName("Realm");
		return basicAuthenticationEntryPoint;
	}

}
