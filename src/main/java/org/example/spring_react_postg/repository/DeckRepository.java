package org.example.spring_react_postg.repository;

import org.example.spring_react_postg.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Integer> {
    List<Deck> findByUserId(Integer userId);
}
