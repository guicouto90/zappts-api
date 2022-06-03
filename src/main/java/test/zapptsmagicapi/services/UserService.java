package test.zapptsmagicapi.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.entities.Deck;
import test.zapptsmagicapi.entities.User;
import test.zapptsmagicapi.exceptions.ExceptionBadRequest;
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

  // METHODS FOR VERIFICATIONS

  public void verifyUserName(String userName) {
    List<User> users = this.userRepository.findAll();
    for(User user : users) {
      if(user.getUserName().equals(userName)) {
        throw new ExceptionBadRequest("userName already exists, please register another one");
      }
    }
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

  public Card verifyCardInDeck(Deck deck, Integer cardId) {
    Card cardExistent = new Card();
    for(Card card : deck.getCards()) {
      if(cardId == card.getId()) {
        cardExistent = card;
      }
    }
    if(cardExistent.getId() == null) {
      throw new ExceptionNotFound("Card not found");
    } else {
      return cardExistent;
    }
  }

  public void verifyPrice(Double price) {
    if(price == null) {
      throw new ExceptionBadRequest("Price cannot be null type");
    }
  }

  public int verifyIndexCard(Deck deck, Integer cardId) {
    int index = 0;
    int indexArray = 0;
    for(Card card : deck.getCards()) {
      if(cardId == card.getId()) {
        indexArray = index;
      }
      index += 1;
    }
    return indexArray;
  }

  public Card verifyCard(Integer id) {
    return this.cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Card not found"));
  }

  public void verifyDeckName(User user, String deckName) {
    for(Deck deck : user.getDecks()) {
      if(deck.getDeckName().equals(deckName)) {
        throw new ExceptionBadRequest("Deckname already exists for your user, please try another one");
      }
    }
  }

  public void verifyIfCardHasRelation(Integer cardId) {
    Card card = this.cardRepository.findById(cardId).get();
    if(card.getDeck() != null) {
      throw new ExceptionBadRequest("You are not allowed to add this card in your deck");
    }
  }

  // **** METHODS FOR CRUD ****

  // List all users
  public List<User> listAllUsers() {
    return this.userRepository.findAll();
  }

  // Add a new user
  public User addUser(User user) {
    this.verifyUserName(user.getUserName());
    return this.userRepository.save(user);
  }

  // Remove a user with id
  public void removeUser(Integer id) {
    this.verifyUserById(id);
    this.userRepository.deleteById(id);
  }

  // Add a deck in a user
  public User addDeckById(Integer id, Deck deck) {
    User user = this.verifyUserById(id);
    this.verifyDeckName(user, deck.getDeckName());
    deck.setUser(user);
    this.deckRepository.save(deck);

    user.addDecks(deck);
    this.userRepository.save(user);

    return user;
  }

  // add a card that already is registered on the system, in a deck;
  public User addCardInDeck(Integer userId, String deckName, Integer cardId) {
    User user = this.verifyUserById(userId);
    Deck deck = this.verifyDeck(user, deckName);
    Card card = this.verifyCard(cardId);

    this.verifyIfCardHasRelation(cardId);
    card.setDeck(deck);
    this.cardRepository.save(card);

    deck.addCards(card);
    this.deckRepository.save(deck);

    return user;
  }

  // edit card price in a deck for an user
  public User editCardInDeck(Integer userId, String deckName, Integer cardId, Card bodyCard) {
    User user = this.verifyUserById(userId);
    Deck deck = this.verifyDeck(user, deckName);
    Card editedCard = this.verifyCardInDeck(deck, cardId);

    this.verifyPrice(bodyCard.getPrice());
    editedCard.setPrice(bodyCard.getPrice());
    this.cardRepository.save(editedCard);

    return user;
  }

  // erase a card in a deck
  public User eraseCardInDeck(Integer userId, String deckName, Integer cardId) {
    User user = this.verifyUserById(userId);
    Deck deck = this.verifyDeck(user, deckName);
    this.verifyCardInDeck(deck, cardId);
    int index = this.verifyIndexCard(deck, cardId);

    deck.removeCard(index);
    this.deckRepository.save(deck);
    
    return user;
  }

  // erase an especific deck
  public User eraseDeck(Integer userId, String deckName) {
    User user = this.verifyUserById(userId);
    Deck deck = this.verifyDeck(user, deckName);

    int index = user.getDecks().indexOf(deck);

    if(index == -1) {
      throw new ExceptionNotFound("Deck not found");
    }
    
    user.removeDeck(index);
    this.userRepository.save(user);

    return user;
  }

  // edit the name of an especific deck
  public User editDeck(Integer userId, String deckName, Deck deck) {
    User user = this.verifyUserById(userId);
    this.verifyDeckName(user, deck.getDeckName());
    Deck editedDeck = this.verifyDeck(user, deckName);

    editedDeck.setDeckName(deck.getDeckName());
    this.deckRepository.save(editedDeck);

    return user;
  }

}
