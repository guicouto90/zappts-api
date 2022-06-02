package test.zapptsmagicapi.repositories;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import test.zapptsmagicapi.entities.Deck;

public interface DeckRepository extends JpaRepository<Deck, Integer> {
  // List<Deck> findDeckByName(String name);
}
