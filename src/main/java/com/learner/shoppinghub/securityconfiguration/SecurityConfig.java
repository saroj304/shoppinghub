package com.learner.shoppinghub.securityconfiguration;

import com.learner.shoppinghub.service.CustomUserDetailsService;
import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomUserDetailsService customUserDetailService;

    /*
        @Override
        protected void configure(HttpSecurity http) throws Exception {
       http
       .authorizeHttpRequests()
    //   place /static/** or images/** for user who are not logged in can access the static images
       .antMatchers("/shop","/register","/login","/forgotpassword","/processforgoutpassword","/images/**","/productimages/**","/shop/viewproduct/**").permitAll()
       .antMatchers("/admin/**").hasRole("ADMIN")
       .anyRequest()
       .authenticated()
       .and()
       .formLogin()
       .loginPage("/login")
       .loginProcessingUrl("/processlogin")
       .defaultSuccessUrl("/home", true)
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
            //daoauthentication
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
        */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/shop", "/register", "/login", "/forgotpassword", "/processforgotpassword", "/images/**", "/productimages/**", "/shop/viewproduct/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .csrf(csrf -> csrf.disable()); // Consider enabling if you’re not testing locally

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService); // set the UserDetailsService
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }


}
