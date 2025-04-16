package org.example.spring_react_postg.security.service;

import org.example.spring_react_postg.model.Card;
import org.example.spring_react_postg.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(Integer id) {
        return cardRepository.findById(id);
    }

    public Optional<Card> getCardByFront(String front) {
        return cardRepository.findByFront(front);
    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }
    public List<Card> getCardsByDeckId(Integer deckId) {
        return cardRepository.findByDeckId(deckId);
    }
}