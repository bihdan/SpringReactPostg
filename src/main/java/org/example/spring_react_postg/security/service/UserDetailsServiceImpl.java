package org.example.spring_react_postg.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.spring_react_postg.model.User;
import org.example.spring_react_postg.repository.UserRepository;

/**
 * Реалізація сервісу {@link UserDetailsService}, який забезпечує завантаження користувача
 * з бази даних за його ім'ям користувача для використання в механізмі автентифікації Spring Security.
 *
 * Повертає користувача у вигляді {@link UserDetails}, що містить необхідну інформацію для автентифікації.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Завантажує користувача з бази даних за його ім'ям користувача.
     * Якщо користувач не знайдений, викидається {@link UsernameNotFoundException}.
     *
     * @param username ім'я користувача (логін)
     * @return {@link UserDetails} користувача, якщо він знайдений
     * @throws UsernameNotFoundException якщо користувач не знайдений за вказаним ім'ям
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
