package com.example.JwtAuthentication.Config;

import com.example.JwtAuthentication.Jwt.JWTAuthenticationFilter;
import com.example.JwtAuthentication.Jwt.JwtAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityFilterConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JWTAuthenticationFilter filter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
           return security.csrf(csrf->csrf.disable())
                   .cors(cors->cors.disable())
                   .authorizeHttpRequests(auth->auth.requestMatchers("auth/login").permitAll()
                           .requestMatchers("auth/create-employee")
                           .permitAll()
                           .anyRequest().authenticated())
                   .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                   .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                   .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
