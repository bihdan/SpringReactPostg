package org.example.spring_react_postg.security.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import org.example.spring_react_postg.security.service.UserDetailsServiceImpl;

/**
 * Фільтр для перевірки JWT токенів.
 * Перевіряє наявність та валідність JWT в заголовку запиту. Якщо токен валідний,
 * встановлює аутентифікацію користувача у {@link SecurityContextHolder}.
 */
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    /**
     * Фільтрує вхідний запит для перевірки наявності та валідності JWT токену.
     * Якщо токен валідний, аутентифікує користувача та встановлює його в {@link SecurityContextHolder}.
     *
     * @param request запит, який обробляється
     * @param response відповідь, яку буде надано
     * @param filterChain ланцюг фільтрів, що буде викликано після цього фільтру
     * @throws ServletException якщо сталася помилка обробки запиту
     * @throws IOException якщо сталася помилка вводу/виводу
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Парсить JWT токен з заголовка {@code Authorization}.
     * Токен має починатися з префікса "Bearer ".
     *
     * @param request запит, з якого буде витягуватись токен
     * @return JWT токен або {@code null}, якщо токен не знайдений
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
