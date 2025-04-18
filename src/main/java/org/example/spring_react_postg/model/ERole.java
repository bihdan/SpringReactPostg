package org.example.spring_react_postg.model;

/**
 * Перелік ролей користувачів у системі.
 * <p>
 * Використовується для визначення рівня доступу користувача.
 */
public enum ERole {
    /**
     * Звичайний користувач з базовими правами доступу.
     */
    ROLE_USER,

    /**
     * Модератор із розширеними правами управління контентом.
     */
    ROLE_MODERATOR,

    /**
     * Адміністратор із повним доступом до системи.
     */
    ROLE_ADMIN
}

