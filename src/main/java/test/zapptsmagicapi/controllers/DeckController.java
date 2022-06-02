package test.zapptsmagicapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.zapptsmagicapi.entities.Deck;
import test.zapptsmagicapi.services.DeckService;

@RestController
@RequestMapping("/decks")
public class DeckController {
  
  @Autowired
  private DeckService deckService;

  @GetMapping
  public ResponseEntity<?> getAllDecks() {
    List<Deck> decks = this.deckService.listAllDeck();
    return new ResponseEntity<>(decks, HttpStatus.OK);
  }

  @GetMapping("/{deckId}")
  public ResponseEntity<Deck> getDeckById(@PathVariable Integer deckId) {
    Deck deck = this.deckService.listDeckById(deckId);
    return new ResponseEntity<>(deck, HttpStatus.OK);
  }

  /*@GetMapping("/{deckName}")
  public ResponseEntity<?> getDeckByName(@PathVariable String deckName) {
    List<Deck> deck = this.deckService.listDecksByName(deckName);
    return new ResponseEntity<>(deck, HttpStatus.OK);
  }*/
}
