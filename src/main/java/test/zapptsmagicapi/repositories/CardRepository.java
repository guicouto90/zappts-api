package test.zapptsmagicapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import test.zapptsmagicapi.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
  
}
