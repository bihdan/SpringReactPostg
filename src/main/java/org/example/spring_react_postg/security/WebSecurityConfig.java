package org.example.spring_react_postg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.example.spring_react_postg.security.jwt.AuthEntryPointJwt;
import org.example.spring_react_postg.security.jwt.AuthTokenFilter;
import org.example.spring_react_postg.security.service.UserDetailsServiceImpl;

/**
 * Конфігурація безпеки для веб-додатку.
 * <p>
 * Цей клас визначає налаштування для автентифікації та авторизації, використовуючи JWT токени.
 * Встановлюється необхідне налаштування для шифрування паролів, автентифікації через DAO,
 * а також правила доступу для різних маршрутів.
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    /**
     * Фільтр для обробки JWT токенів.
     * <p>
     * Цей фільтр додається до ланцюга фільтрів для перевірки JWT токенів у кожному запиті.
     *
     * @return {@link AuthTokenFilter} об'єкт фільтру.
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    /**
     * Налаштування провайдера автентифікації через DAO.
     * <p>
     * Цей метод конфігурує провайдера автентифікації для взаємодії з {@link UserDetailsServiceImpl} та
     * використання шифрування паролів за допомогою {@link BCryptPasswordEncoder}.
     *
     * @return {@link DaoAuthenticationProvider} провайдер автентифікації.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Створення об'єкта {@link AuthenticationManager}.
     * <p>
     * Цей метод налаштовує {@link AuthenticationManager}, який використовується для обробки автентифікації.
     *
     * @param authConfig {@link AuthenticationConfiguration} конфігурація автентифікації.
     * @return {@link AuthenticationManager} менеджер автентифікації.
     * @throws Exception якщо виникає помилка при створенні менеджера автентифікації.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Створення об'єкта {@link PasswordEncoder} для шифрування паролів.
     * <p>
     * Використовується для шифрування паролів користувачів перед їх збереженням у базі даних.
     *
     * @return {@link BCryptPasswordEncoder} шифрувальник паролів.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Налаштування фільтрації запитів та управління сесіями.
     * <p>
     * Цей метод налаштовує правила доступу для маршрутів API, обробляє CORS, CSRF, а також
     * визначає політику створення сесій як "STATLESS" для використання JWT токенів.
     *
     * @param http {@link HttpSecurity} об'єкт для налаштування безпеки HTTP.
     * @return {@link SecurityFilterChain} об'єкт для обробки фільтрації запитів.
     * @throws Exception якщо виникає помилка при налаштуванні безпеки.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/test/**").permitAll()
                        .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
