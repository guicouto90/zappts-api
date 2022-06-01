package test.zapptsmagicapi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Deck {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @JsonManagedReference
  @OneToMany(mappedBy = "deck", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Card> cards;

  @NotEmpty(message = "Please, provide a deckName")
  private String deckName;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Deck() {
    super();
    this.cards = new ArrayList<>();
  }

  public Deck(Integer id, String deckName, User user) {
    this.id = id;
    this.deckName = deckName;
    this.user = user;
    this.cards = new ArrayList<>();
  }

  public Integer getId() {
    return this.id;
  }

  public List<Card> getCards() {
    return this.cards;
  }

  public void addCards(Card card) {
    this.cards.add(card);
  }

  public String getDeckName() {
    return deckName;
  }

  public void setDeckName(String deckName) {
    this.deckName = deckName;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
