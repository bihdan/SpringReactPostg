package org.example.spring_react_postg.payload.response;

import java.util.List;

/**
 * Відповідь, що містить JWT токен та інформацію про користувача після автентифікації.
 * <p>
 * Цей клас передає дані токену доступу, тип токену, ідентифікатор, ім’я користувача, електронну пошту
 * та ролі користувача після успішної автентифікації.
 */
public class JwtResponse {
    /**
     * JWT токен доступу.
     */
    private String token;

    /**
     * Тип токену (за замовчуванням "Bearer").
     */
    private String type = "Bearer";

    /**
     * Ідентифікатор користувача.
     */
    private int id;

    /**
     * Ім’я користувача.
     */
    private String username;

    /**
     * Електронна пошта користувача.
     */
    private String email;

    /**
     * Список ролей користувача.
     */
    private List<String> roles;

    /**
     * Конструктор для створення відповіді з даними автентифікації.
     *
     * @param accessToken токен доступу
     * @param id ідентифікатор користувача
     * @param username ім’я користувача
     * @param email електронна пошта користувача
     * @param roles ролі користувача
     */
    public JwtResponse(String accessToken, int id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * Повертає токен доступу.
     *
     * @return токен доступу
     */
    public String getAccessToken() {
        return token;
    }

    /**
     * Встановлює токен доступу.
     *
     * @param accessToken токен доступу
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * Повертає тип токену.
     *
     * @return тип токену
     */
    public String getTokenType() {
        return type;
    }

    /**
     * Встановлює тип токену.
     *
     * @param tokenType тип токену
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * Повертає ідентифікатор користувача.
     *
     * @return ідентифікатор користувача
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор користувача.
     *
     * @param id ідентифікатор користувача
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Повертає електронну пошту користувача.
     *
     * @return електронна пошта користувача
     */
    public String getEmail() {
        return email;
    }

    /**
     * Встановлює електронну пошту користувача.
     *
     * @param email електронна пошта користувача
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Повертає ім’я користувача.
     *
     * @return ім’я користувача
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює ім’я користувача.
     *
     * @param username ім’я користувача
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Повертає список ролей користувача.
     *
     * @return список ролей
     */
    public List<String> getRoles() {
        return roles;
    }
}
