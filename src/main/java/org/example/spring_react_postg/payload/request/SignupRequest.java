package org.example.spring_react_postg.payload.request;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;

/**
 * Клас {@code SignupRequest} представляє запит на реєстрацію нового користувача.
 * Містить необхідні поля для створення облікового запису: імʼя користувача, email, роль(і) та пароль.
 * Використовується для обробки вхідних JSON-запитів у контролерах.
 */
public class SignupRequest {

    /**
     * Імʼя користувача.
     * Повинно бути не порожнім, довжиною від 3 до 20 символів.
     */
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    /**
     * Email користувача.
     * Повинен бути у форматі валідної email-адреси, не більше 50 символів.
     */
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    /**
     * Набір ролей, що мають бути присвоєні користувачу.
     * Наприклад: ["user"], ["admin"] або обидві.
     */
    private Set<String> role;

    /**
     * Пароль користувача.
     * Повинен бути не порожнім, довжиною від 6 до 40 символів.
     */
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    /**
     * Повертає імʼя користувача.
     *
     * @return імʼя користувача
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює імʼя користувача.
     *
     * @param username імʼя користувача
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Повертає email користувача.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Встановлює email користувача.
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * Повертає ролі, які мають бути присвоєні користувачу.
     *
     * @return набір ролей
     */
    public Set<String> getRole() {
        return this.role;
    }

    /**
     * Встановлює ролі користувача.
     *
     * @param role набір ролей
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }
}
