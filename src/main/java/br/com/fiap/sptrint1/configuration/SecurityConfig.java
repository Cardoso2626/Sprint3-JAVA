package br.com.fiap.sptrint1.configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/moto/**", "/chaveiro/**", "/localizacao/**", "/funcionario/**", "/patio/**") // só essas rotas
                .csrf(csrf -> csrf.disable()) // desabilita CSRF para APIs
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // libera todas
                .formLogin(form -> form.disable()) // desabilita form login para essas rotas
                .oauth2Login(oauth -> oauth.disable()) // desabilita OAuth login
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                );

        return http.build();
    }
}
