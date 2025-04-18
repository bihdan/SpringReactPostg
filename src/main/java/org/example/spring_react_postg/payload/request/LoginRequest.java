package org.example.spring_react_postg.payload.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO-клас для обробки запиту авторизації користувача.
 * Містить ім'я користувача та пароль.
 */
public class LoginRequest {

    /**
     * Ім'я користувача, яке не може бути порожнім.
     */
    @NotBlank
    private String username;

    /**
     * Пароль користувача, який не може бути порожнім.
     */
    @NotBlank
    private String password;

    /**
     * Повертає ім'я користувача.
     *
     * @return ім'я користувача
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює ім'я користувача.
     *
     * @param username ім'я користувача
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Повертає пароль користувача.
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Встановлює пароль користувача.
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
