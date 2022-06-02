package test.zapptsmagicapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.zapptsmagicapi.entities.Deck;
import test.zapptsmagicapi.repositories.DeckRepository;

@Service
public class DeckService {
  
  @Autowired
  private DeckRepository deckRepository;

  public List<Deck> listAllDeck() {
    return this.deckRepository.findAll();
  }

  public Deck listDeckById(Integer id) {
    return this.deckRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Deck Not Found"));
  }

  /*public List<Deck> listDecksByName(String name) {
    return deckRepository.findDeckByName(name);
  }*/
}
