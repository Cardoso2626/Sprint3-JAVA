package br.com.fiap.sptrint1.configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 🔓 Configuração para APIs REST (sem login)
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/moto/**", "/chaveiro/**", "/localizacao/**", "/funcionario/**", "/patio/**", "/moto/{id}")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(form -> form.disable())
                .oauth2Login(oauth -> oauth.disable())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                )
                .build();
    }

    // 🔒 Configuração para Thymeleaf (GitHub + tela de login do Spring)
    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/").permitAll()
                                        .requestMatchers("/pageFuncionario/lista").permitAll()
                                        .anyRequest().authenticated()
                ).oauth2Login(oauth2 ->
                        oauth2.defaultSuccessUrl("/pageFuncionario/lista"))
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
