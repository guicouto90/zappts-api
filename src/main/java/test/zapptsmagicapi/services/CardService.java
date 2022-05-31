package test.zapptsmagicapi.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.exceptions.ExceptionNotFound;
import test.zapptsmagicapi.exceptions.LanguageException;
import test.zapptsmagicapi.repositories.CardRepository;

@Service
public class CardService {
  
  @Autowired
  private CardRepository cardRepository;

  public void verifyLanguage(String language) {
    if(!language.equals("portuguese") && !language.equals("english") && !language.equals("japanese")) {
      throw new LanguageException("Language field must be filled with portuguese, english or japanese");
    }
  }

  public Card addCard(Card card) {
    this.verifyLanguage(card.getLanguage());
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
