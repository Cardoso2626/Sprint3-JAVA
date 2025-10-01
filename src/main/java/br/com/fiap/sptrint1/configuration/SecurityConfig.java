package br.com.fiap.sptrint1.configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Security chain para os endpoints da API
     * Não exige autenticação, desabilita OAuth2 e form login
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Permite preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Permite todos os endpoints da API
                        .requestMatchers("/moto/**").permitAll()
                        .requestMatchers("/chaveiro/**").permitAll()
                        .requestMatchers("/localizacao/**").permitAll()
                        .requestMatchers("/funcionario/**").permitAll()
                        .requestMatchers("/patio/**").permitAll()
                        // Qualquer outro request da API
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable())
                .oauth2Login(oauth -> oauth.disable())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                );

        return http.build();
    }

    /**
     * Security chain para páginas web
     * Mantém OAuth2 e form login
     */
    @Bean
    @Order(2)
    public SecurityFilterChain webSecurity(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Ignora endpoints da API
                        .requestMatchers("/moto/**", "/chaveiro/**", "/localizacao/**", "/funcionario/**", "/patio/**").permitAll()
                        // Páginas web abertas
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/pageFuncionario/lista").permitAll()
                        // Todas as outras páginas exigem autenticação
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/pageFuncionario/lista"))
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable()) // opcional, dependendo do uso de formulários
                .cors(Customizer.withDefaults());

        return http.build();
    }
}
