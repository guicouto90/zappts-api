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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @JsonManagedReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Deck> decks;

  private String name;
  private String userName;


  public User(Integer id, String name, String userName) {
    this.id = id;
    this.decks = new ArrayList<>();
    this.name = name;
    this.userName = userName;
  }

  public Integer getId() {
    return this.id;
  }

  public List<Deck> getDecks() {
    return this.decks;
  }

  public void setDecks(List<Deck> decks) {
    this.decks = decks;
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
