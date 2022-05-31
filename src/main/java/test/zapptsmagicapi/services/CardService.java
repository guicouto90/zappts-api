package test.zapptsmagicapi.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.exceptions.ExceptionNotFound;
import test.zapptsmagicapi.repositories.CardRepository;

@Service
public class CardService {
  
  @Autowired
  private CardRepository cardRepository;

  public Card addCard(Card card) {
    return this.cardRepository.save(card);
  }

  public List<Card> listAllCards() {
    return this.cardRepository.findAll();
  }

  public Card listCardById(Integer id) {
    try {
      return this.cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Card Not Found"));
    } catch(NoSuchElementException e) {
      throw new ExceptionNotFound("Card not found");
    }
  }

  public void removeCard(Integer id) {
    this.listCardById(id);
    this.cardRepository.deleteById(id);
  }
}
