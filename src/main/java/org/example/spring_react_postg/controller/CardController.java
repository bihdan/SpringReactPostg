package org.example.spring_react_postg.controller;


import org.example.spring_react_postg.model.Card;
import org.example.spring_react_postg.security.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public Optional<Card> getCardById(@PathVariable Integer id) {
        return cardService.getCardById(id);
    }

    @GetMapping("/deck/{deckId}")
    public ResponseEntity<List<Card>> getCardsByDeckId(@PathVariable Integer deckId) {
        List<Card> cards = cardService.getCardsByDeckId(deckId);
        return ResponseEntity.ok(cards);
    }

}
