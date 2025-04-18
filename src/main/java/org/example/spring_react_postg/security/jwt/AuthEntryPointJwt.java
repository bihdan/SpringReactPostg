package org.example.spring_react_postg.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Клас, що реалізує {@link AuthenticationEntryPoint} для обробки несанкціонованих запитів.
 * Використовується для того, щоб повертати повідомлення про помилку з кодом 401 (Unauthorized)
 * у випадку, якщо користувач не авторизований або не має доступу до ресурсу.
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Обробляє помилку автентифікації і генерує відповідь з кодом 401 (Unauthorized).
     * Використовується, коли користувач не авторизований або не має доступу до ресурсу.
     *
     * @param request об'єкт {@link HttpServletRequest}, що містить дані запиту
     * @param response об'єкт {@link HttpServletResponse}, що містить відповідь на запит
     * @param authException виняток автентифікації
     * @throws IOException якщо виникає помилка при обробці відповіді
     * @throws ServletException якщо виникає помилка під час обробки запиту
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }

}
