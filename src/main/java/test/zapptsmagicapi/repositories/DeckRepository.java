package test.zapptsmagicapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import test.zapptsmagicapi.entities.Deck;

public interface DeckRepository extends JpaRepository<Deck, Integer> {
  
}
