package test.zapptsmagicapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Card {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;
  private String edition;
  private String language;
  private boolean foil;
  private double price;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "deck_id")
  private Deck deck;

  public Card(Integer id, String name, String edition, String language, boolean foil, double price) {
    this.id = id;
    this.name = name;
    this.edition = edition;
    this.language = language;
    this.foil = foil;
    this.price = price;
  }

  public Integer getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEdition() {
    return this.edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public boolean isFoil() {
    return this.foil;
  }

  public boolean getFoil() {
    return this.foil;
  }

  public void setFoil(boolean foil) {
    this.foil = foil;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Deck getDeck() {
    return this.deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

}
