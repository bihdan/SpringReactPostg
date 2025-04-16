package org.example.spring_react_postg.controller;

import org.example.spring_react_postg.model.Deck;
import org.example.spring_react_postg.security.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
    @Autowired
    private DeckService deckService;

    @GetMapping
    public ResponseEntity<List<Deck>> getAllDecks() {
        List<Deck> decks = deckService.getAllDecks();
        return ResponseEntity.ok(decks);
    }

    @GetMapping("/user/{userId}")
    public List<Deck> getDecksByUserId(@PathVariable Integer userId) {
        return deckService.getDecksByUserId(userId);
    }

    @GetMapping("/{id}")
    public Optional<Deck> getDeckById(@PathVariable Integer id) {
        return deckService.getDeckById(id);
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck) {
        return deckService.saveDeck(deck);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDeck(@PathVariable Integer id) {
        deckService.deleteDeck(id);
    }
}
