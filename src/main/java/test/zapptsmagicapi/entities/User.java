package test.zapptsmagicapi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty(message = "Please provide a name, cant be empty")
  private String name;
  
  @NotEmpty(message = "Please provide a username, cant be empty")
  private String userName;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Deck> decks;

  public User() {
    super();
    this.decks = new ArrayList<>();
  }

  public User(Integer id, String name, String userName) {
    this.id = id;
    this.name = name;
    this.userName = userName;
    this.decks = new ArrayList<>();
  }

  public Integer getId() {
    return this.id;
  }

  public List<Deck> getDecks() {
    return this.decks;
  }

  public void addDecks(Deck deck) {
    this.decks.add(deck);
  }

  public void removeDeck(int index) {
    this.decks.remove(index);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
