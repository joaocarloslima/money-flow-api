package br.com.fiap.money_flow_api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                    .requestMatchers("/transactions/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(List.of(
            User
                .withUsername("joao")
                .password("$2a$12$26f425/HkvO46.HvCTzGye7TCzN0Xgnd6staV/nA8gBsEkJUPFpse")
                .roles("ADMIN")
                .build(), 
            User
                .withUsername("maria")
                .password("$2a$12$eaRCNjPrNrUb319lJiUnVe0ZmHOiBOcsTReSMlLncft.Utm4y5YYe")
                .roles("USER")
                .build()
        ));
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
