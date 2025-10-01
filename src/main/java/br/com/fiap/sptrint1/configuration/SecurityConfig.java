package br.com.fiap.sptrint1.configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] API_PATHS = {
            "/moto/**",
            "/chaveiro/**",
            "/localizacao/**",
            "/funcionario/**",
            "/patio/**"
    };

    /**
     * Security chain para os endpoints da API (REST)
     * Não exige autenticação (permitAll), desabilita OAuth2 e form login.
     * Usa securityMatcher() para se aplicar APENAS aos caminhos REST.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http
                // *** CORREÇÃO: Define o escopo, separando a API do escopo web ***
                .securityMatcher(API_PATHS)

                // Desabilita CSRF, pois APIs REST geralmente não precisam
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> auth
                        // Permite preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Permite todos os endpoints da API (REST)
                        .requestMatchers(API_PATHS).permitAll()
                )
                // Desabilita mecanismos de login para forçar o comportamento 'permitAll'
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login(AbstractHttpConfigurer::disable)

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, e) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                );

        return http.build();
    }

    /**
     * Security chain para páginas web (Thymeleaf/Render)
     * Mantém OAuth2 e form login.
     * NÃO usa securityMatcher(), atuando como filtro "catch-all" para o RESTANTE das requisições.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain webSecurity(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Páginas web abertas
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/pageFuncionario/lista").permitAll()
                        // Todas as outras páginas exigem autenticação
                        .anyRequest().authenticated()
                )
                // Habilita as formas de autenticação para o Web/Thymeleaf
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/pageFuncionario/lista"))
                .formLogin(Customizer.withDefaults())

                // Opções de segurança padrão para web
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());

        return http.build();
    }
}