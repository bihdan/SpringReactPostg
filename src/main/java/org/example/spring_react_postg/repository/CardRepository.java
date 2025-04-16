package org.example.spring_react_postg.repository;

import org.example.spring_react_postg.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByDeckId(Integer DeckId);
    Optional<Card> findByFront(String front);
}
