package org.example.spring_react_postg.repository;

import org.example.spring_react_postg.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторій для роботи з об'єктами типу {@link Card}.
 * Використовує JPA для доступу до бази даних і надає методи для виконання запитів.
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    /**
     * Знаходить список карток за ідентифікатором колоди.
     *
     * @param DeckId Ідентифікатор колоди.
     * @return Список карток, що належать до вказаної колоди.
     */
    List<Card> findByDeckId(Integer DeckId);

    /**
     * Знаходить картку за її лицьовою стороною (фронтом).
     *
     * @param front Текст, що міститься на лицьовій стороні картки.
     * @return Опційний об'єкт картки, що відповідає заданому фронту.
     */
    Optional<Card> findByFront(String front);
}
