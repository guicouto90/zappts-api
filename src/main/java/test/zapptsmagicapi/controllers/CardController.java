package test.zapptsmagicapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.services.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {
  
  @Autowired
  private CardService cardService;

  @GetMapping
  public ResponseEntity<?> getAllCards() {
    List<Card> cards = this.cardService.listAllCards();
    return new ResponseEntity<>(cards, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Card> insertCard(@Valid @RequestBody Card card) {
    Card newCard = this.cardService.addCard(card);
    return new ResponseEntity<>(newCard, HttpStatus.CREATED); 
  }

  @GetMapping("/{cardId}")
  public ResponseEntity<Card> getCardById(@PathVariable Integer cardId) {
    Card card = this.cardService.listCardById(cardId);
    return new ResponseEntity<>(card, HttpStatus.OK);
  }

  @DeleteMapping("/{cardId}")
  public ResponseEntity<String> deleteCardById(@PathVariable Integer cardId) {
    this.cardService.removeCard(cardId);
    return new ResponseEntity<String>("Card with id " + String.valueOf(cardId)  + " deleted",HttpStatus.ACCEPTED);
  }
}
