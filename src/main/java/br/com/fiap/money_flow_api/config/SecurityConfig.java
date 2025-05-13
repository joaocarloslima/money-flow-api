package br.com.fiap.money_flow_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/transactions/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .build();
    }

    // @Bean
    // UserDetailsService userDetailsService(){
    // return new InMemoryUserDetailsManager(List.of(
    // User
    // .withUsername("joao")
    // .password("$2a$12$bTQhrOKvy8u.41Z6MCtAWO324bULDah.LrXdFZ/aWkS9gY0UYRS0G")
    // .roles("ADMIN")
    // .build(),
    // User
    // .withUsername("maria")
    // .password("$2a$12$nuQqQOe3hA5jWi4bCWQ8bedArKPWo45Mvv1n2kulO/r7AjMwJHxvm")
    // .roles("USER")
    // .build()
    // ));
    // }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
