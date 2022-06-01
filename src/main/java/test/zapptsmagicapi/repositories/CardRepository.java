package test.zapptsmagicapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import test.zapptsmagicapi.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
  List<Card> findCardByName(String name);
}
