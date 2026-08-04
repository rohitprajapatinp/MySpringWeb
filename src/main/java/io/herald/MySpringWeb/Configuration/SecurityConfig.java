package io.herald.MySpringWeb.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable());    //HTTP build garnu aghi http ma csrf bhanne
        //function disable gareko http build garera return garnu xa - securityfilterchain lai
        http.authorizeHttpRequests(auth-> auth.anyRequest().permitAll());
        return http.build();
    }
}
