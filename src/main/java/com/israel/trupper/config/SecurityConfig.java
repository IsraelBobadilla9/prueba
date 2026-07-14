package com.israel.trupper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.israel.trupper.security.JwtFilter;

@Configuration
@EnableWebSecurity
// Proteccion a metodos no activada se hace a nivel de rutas 
public class SecurityConfig {
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http , JwtFilter jwtFilter) throws Exception{
       return  http.csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/auth/login").permitAll()
            .requestMatchers(HttpMethod.PUT, "/productos/**").permitAll()
            .anyRequest().authenticated();
        }).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails user = User.builder().username("username").password(encoder.encode("username123")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(encoder.encode("admin123")).roles("ADMIN","USER").build();
        return new InMemoryUserDetailsManager(user,admin);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    
}
