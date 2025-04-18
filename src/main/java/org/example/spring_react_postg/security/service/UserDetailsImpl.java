package org.example.spring_react_postg.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.example.spring_react_postg.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Реалізація інтерфейсу {@link UserDetails} для користувача в контексті автентифікації та авторизації.
 * <p>
 * Цей клас використовується Spring Security для зберігання інформації про користувача, такої як ім'я, електронна пошта, пароль і ролі.
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String email;

    /**
     * Пароль користувача, який буде ігноруватись при серіалізації в JSON.
     */
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Конструктор для створення об'єкта {@link UserDetailsImpl}.
     *
     * @param id унікальний ідентифікатор користувача
     * @param username ім'я користувача
     * @param email електронна пошта користувача
     * @param password пароль користувача
     * @param authorities колекція ролей користувача
     */
    public UserDetailsImpl(int id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Створює об'єкт {@link UserDetailsImpl} на основі об'єкта {@link User}.
     *
     * @param user об'єкт користувача
     * @return {@link UserDetailsImpl}, створений на основі даних користувача
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
