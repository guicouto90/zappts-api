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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.entities.Deck;
import test.zapptsmagicapi.entities.User;
import test.zapptsmagicapi.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    List<User> users = this.userService.listAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
    User user = this.userService.verifyUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> insertUser(@Valid @RequestBody User user) {
    User newUser = this.userService.addUser(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @PostMapping("/{userId}/decks")
  public ResponseEntity<User> insertDeck(@PathVariable Integer userId, @Valid @RequestBody Deck deck) {
    User user = this.userService.addDeckById(userId, deck);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @DeleteMapping("/{userId}/decks/{deckName}")
  public ResponseEntity<User> deleteDeck(@PathVariable Integer userId,@PathVariable String deckName) {
    User user = this.userService.eraseDeck(userId, deckName);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @PutMapping("/{userId}/decks/{deckName}")
  public ResponseEntity<User> updateDeck(@PathVariable Integer userId, @PathVariable String deckName, @Valid @RequestBody Deck deck) {
    User user = this.userService.editDeck(userId, deckName, deck);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @PostMapping("/{userId}/decks/{deckName}/cards/{cardId}")
  public ResponseEntity<User> insertCardInDeck(@PathVariable Integer userId, @PathVariable String deckName, @PathVariable Integer cardId) {
    User user = this.userService.addCardInDeck(userId, deckName, cardId);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PutMapping("/{userId}/decks/{deckName}/cards/{cardId}")
  public ResponseEntity<User> updateCardInDeck(@PathVariable Integer userId, @PathVariable String deckName, @PathVariable Integer cardId, @RequestBody Card card) {
    User user = this.userService.editCardInDeck(userId, deckName, cardId, card);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{userId}/decks/{deckName}/cards/{cardId}")
  public ResponseEntity<?> deleteCardInDeck(@PathVariable Integer userId, @PathVariable String deckName, @PathVariable Integer cardId) {
    User user = this.userService.eraseCardInDeck(userId, deckName, cardId);
    return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) {
    this.userService.removeUser(userId);
    return new ResponseEntity<String>("User with id " + String.valueOf(userId) + " deleted", HttpStatus.ACCEPTED);
  }
}
