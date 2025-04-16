package org.example.spring_react_postg.security.service;

import org.example.spring_react_postg.model.Deck;
import org.example.spring_react_postg.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {

    private final DeckRepository deckRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public List<Deck> getDecksByUserId(Integer userId) {
        return deckRepository.findByUserId(userId);
    }

    public List<Deck> getDecksByNameAndUserId(String name, Integer userId) {
        return deckRepository.findByUserId(userId); // findByNameAndUserId
    }

    public Optional<Deck> getDeckById(Integer id) {
        return deckRepository.findById(id);
    }

    public Deck saveDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    public void deleteDeck(Integer id) {
        deckRepository.deleteById(id);
    }
}
