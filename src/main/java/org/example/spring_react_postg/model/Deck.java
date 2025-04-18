package org.example.spring_react_postg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Сутність {@code Deck} представляє колоду карт, яка належить певному користувачу.
 * Використовується для збереження даних у таблиці {@code deck} бази даних.
 */
@Entity
@Table(name = "deck")
@NoArgsConstructor
@AllArgsConstructor
public class Deck {

    /**
     * Унікальний ідентифікатор колоди.
     */
    @Id
    private Integer id;

    /**
     * Користувач, якому належить ця колода.
     * Зовнішній ключ до таблиці {@code user}.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;

    /**
     * Назва колоди.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Повертає ідентифікатор колоди.
     *
     * @return ідентифікатор
     */
    public Integer getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор колоди.
     *
     * @param id ідентифікатор
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Повертає користувача, якому належить колода.
     *
     * @return обʼєкт {@link User}
     */
    public User getUser() {
        return user;
    }

    /**
     * Встановлює користувача, якому належить колода.
     *
     * @param user обʼєкт {@link User}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Повертає назву колоди.
     *
     * @return назва
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву колоди.
     *
     * @param name назва
     */
    public void setName(String name) {
        this.name = name;
    }
}
