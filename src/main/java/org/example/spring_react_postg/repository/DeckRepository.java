package org.example.spring_react_postg.repository;

import org.example.spring_react_postg.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторій для роботи з таблицею {@code deck}.
 * Використовується для виконання операцій з колодами карт у базі даних.
 */
@Repository
public interface DeckRepository extends JpaRepository<Deck, Integer> {

    /**
     * Знаходить колоди, що належать певному користувачу за його ідентифікатором.
     *
     * @param userId ідентифікатор користувача
     * @return список колод, які належать користувачу з вказаним {@code userId}
     */
    List<Deck> findByUserId(Integer userId);
}
