package com.learner.shoppinghub.securityconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learner.shoppinghub.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
CustomUserDetailsService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
   http
   .authorizeRequests()
   .antMatchers("/shop","/register","/login").permitAll()
   .antMatchers("/admin/**").hasRole("ADMIN")
   .anyRequest()
   .authenticated()
   .and()
   .formLogin()
   .loginPage("/login")
   .loginProcessingUrl("/processlogin")
   .defaultSuccessUrl("/home")
   .failureUrl("/login?error=true")
   .usernameParameter("email")
   .passwordParameter("password")
   .permitAll()
   .and()
   .logout()
   .logoutUrl("/logout")
   .invalidateHttpSession(true)
   .deleteCookies("JSESSIONID")
   .and()
   .exceptionHandling()
   .and()
   .csrf()
   .disable()
   .httpBasic();
	}
	
//	//Custom userDetailService ma vako information lae retrieve gar and Customuserdetail object  lae pass it to the daoAuthentication provider
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.userDetailsService(customUserDetailService)
//		.passwordEncoder(passwordEncoder());
//	}
	
//provide the userdetialservice and passwordencoder to the dao authentication provider
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider daoauth=new DaoAuthenticationProvider();
		daoauth.setUserDetailsService(customUserDetailService);
		daoauth.setPasswordEncoder(passwordEncoder());
		return daoauth;
	}
	//encrypt the password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	configure method
	//AuthenticationManagerBuilder lae daoauthenticationProvider ko object dina ra yesla authenticate garxa
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.authenticationProvider(daoAuthenticationProvider());
	}

	//ignore the endpoints inside this method means Endpoints specified in this method will be ignored by Spring Security, meaning it will by-pass the Spring Security Filter Chain and no security context will be set.
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/**");
	}
	
	
	
}
