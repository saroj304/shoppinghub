package com.learner.shoppinghub.securityconfiguration;

import com.learner.shoppinghub.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/forgotpassword", "/processforgotpassword", "/images/**", "/productimages/**", "/shop/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/shop", true)
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                ) .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login?logout=true") // Optional: add logout success URL
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /* @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                 .authorizeHttpRequests(auth -> auth
                         .requestMatchers( "/register", "/login", "/forgotpassword", "/processforgotpassword", "/images/**", "/productimages/**", "/shop/**").permitAll()

                         .requestMatchers("/admin/**").hasAuthority("ADMIN")
                         .anyRequest().authenticated()
                 )
                 .formLogin(form -> form
                         .loginPage("/login").permitAll()
                         .loginProcessingUrl("/perform_login")
                         .defaultSuccessUrl("/shop", true)
                         .failureUrl("/login?error=true")
                         .usernameParameter("email")
                         .passwordParameter("password")
                         .permitAll()
                 )
                 .logout(logout -> logout
                         .logoutUrl("/logout")
                         .invalidateHttpSession(true)
                         .deleteCookies("JSESSIONID")
                         .logoutSuccessUrl("/login?logout=true") // Optional: add logout success URL
                 )
                 .sessionManagement(session -> session
                         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                 )

                 .csrf(AbstractHttpConfigurer::disable);
     // Consider enabling CSRF protection in production
         return http.build();
     }*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
