package org.example.spring_react_postg.security.service;

import org.example.spring_react_postg.model.Deck;
import org.example.spring_react_postg.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервіс для роботи з колодами карт.
 * Містить методи для виконання операцій з колодами, таких як отримання, збереження та видалення.
 */
@Service
public class DeckService {

    private final DeckRepository deckRepository;

    /**
     * Конструктор для ініціалізації {@link DeckService} з ін'єкцією {@link DeckRepository}.
     *
     * @param deckRepository репозиторій для роботи з колодами карт
     */
    @Autowired
    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    /**
     * Повертає список всіх колод.
     *
     * @return список всіх колод
     */
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    /**
     * Повертає список колод, що належать певному користувачу за його ідентифікатором.
     *
     * @param userId ідентифікатор користувача
     * @return список колод, що належать користувачу
     */
    public List<Deck> getDecksByUserId(Integer userId) {
        return deckRepository.findByUserId(userId);
    }

    /**
     * Повертає список колод за ім'ям та ідентифікатором користувача.
     * (Зараз використовує метод для пошуку лише за ідентифікатором користувача, не за ім'ям.)
     *
     * @param name ім'я колоди
     * @param userId ідентифікатор користувача
     * @return список колод, що відповідають пошуку
     */
    public List<Deck> getDecksByNameAndUserId(String name, Integer userId) {
        return deckRepository.findByUserId(userId); // має бути findByNameAndUserId
    }

    /**
     * Повертає колоду за її ідентифікатором.
     *
     * @param id ідентифікатор колоди
     * @return Optional з колодою або пустий Optional, якщо колоду не знайдено
     */
    public Optional<Deck> getDeckById(Integer id) {
        return deckRepository.findById(id);
    }

    /**
     * Зберігає нову або оновлену колоду.
     *
     * @param deck об'єкт колоди для збереження
     * @return збережена колода
     */
    public Deck saveDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    /**
     * Видаляє колоду за її ідентифікатором.
     *
     * @param id ідентифікатор колоди для видалення
     */
    public void deleteDeck(Integer id) {
        deckRepository.deleteById(id);
    }
}
