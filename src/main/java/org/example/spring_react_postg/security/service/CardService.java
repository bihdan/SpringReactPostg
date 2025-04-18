package org.example.spring_react_postg.security.service;

import org.example.spring_react_postg.model.Card;
import org.example.spring_react_postg.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервіс для роботи з картками.
 * Використовує {@link CardRepository} для виконання операцій з базою даних,
 * таких як отримання карток, їх збереження та фільтрація.
 */
@Service
public class CardService {
    private final CardRepository cardRepository;

    /**
     * Конструктор для ін'єкції залежностей через {@link CardRepository}.
     *
     * @param cardRepository Репозиторій для карток.
     */
    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Отримує всі картки з бази даних.
     *
     * @return Список всіх карток.
     */
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    /**
     * Отримує картку за її ідентифікатором.
     *
     * @param id Ідентифікатор картки.
     * @return Опційний об'єкт картки з вказаним ідентифікатором.
     */
    public Optional<Card> getCardById(Integer id) {
        return cardRepository.findById(id);
    }

    /**
     * Отримує картку за її лицьовою стороною (фронтом).
     *
     * @param front Текст, що міститься на лицьовій стороні картки.
     * @return Опційний об'єкт картки з вказаним фронтом.
     */
    public Optional<Card> getCardByFront(String front) {
        return cardRepository.findByFront(front);
    }

    /**
     * Зберігає картку в базі даних.
     *
     * @param card Картка для збереження.
     * @return Збережена картка.
     */
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    /**
     * Отримує картки за ідентифікатором колоди.
     *
     * @param deckId Ідентифікатор колоди.
     * @return Список карток, що належать до вказаної колоди.
     */
    public List<Card> getCardsByDeckId(Integer deckId) {
        return cardRepository.findByDeckId(deckId);
    }
}
