package test.zapptsmagicapi.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.entities.Deck;
import test.zapptsmagicapi.entities.User;
import test.zapptsmagicapi.exceptions.ExceptionNotFound;
import test.zapptsmagicapi.repositories.CardRepository;
import test.zapptsmagicapi.repositories.DeckRepository;
import test.zapptsmagicapi.repositories.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DeckRepository deckRepository;

  @Autowired
  private CardRepository cardRepository;

  public User addUser(User user) {
    return this.userRepository.save(user);
  }

  public List<User> listAllUsers() {
    return this.userRepository.findAll();
  }

  public User verifyUserById(Integer id) {
    try {
      return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
    } catch(NoSuchElementException e) {
      throw new ExceptionNotFound("User not found");
    }
  }

  public Deck verifyDeck(User user, String deckName) {
    Deck deckExistent = new Deck();
    for(Deck deck : user.getDecks()) {
      if(deckName.equals(deck.getDeckName())) {
        deckExistent = deck;
      }
    }
    if(deckExistent.getDeckName() == null) {
      throw new ExceptionNotFound("Deck not found");
    } else {
      return deckExistent;
    }
  }

  public Card verifyCard(Integer id) {
    return this.cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Card not found"));
  }

  public void removeUser(Integer id) {
    this.verifyUserById(id);
    this.userRepository.deleteById(id);
  }

  public User addDeckById(Integer id, Deck deck) {
    User user = this.verifyUserById(id);
    deck.setUser(user);
    this.deckRepository.save(deck);

    user.addDecks(deck);
    this.userRepository.save(user);

    return user;
  }

  public User addCardInDeck(Integer userId, String deckName, Integer cardId) {
    User user = this.verifyUserById(userId);
    Deck deck = this.verifyDeck(user, deckName);
    Card card = this.verifyCard(cardId);

    card.setDeck(deck);
    this.cardRepository.save(card);

    deck.addCards(card);
    this.deckRepository.save(deck);

    return user;
  }
}
