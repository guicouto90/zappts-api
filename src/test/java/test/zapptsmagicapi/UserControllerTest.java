package test.zapptsmagicapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

// import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.entities.Deck;
import test.zapptsmagicapi.entities.User;
// import test.zapptsmagicapi.repositories.CardRepository;
import test.zapptsmagicapi.repositories.UserRepository;
import test.zapptsmagicapi.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  /* @Autowired
  private CardRepository cardRepository; */

  @BeforeEach
  public void setup() {
    this.userRepository.deleteAll();
  }
  
  @Test
  void testAddNewUser() throws Exception {
    User user = new User(null, "Guilherme", "te4ste");

    mockMvc
      .perform(post("/user").contentType(MediaType.APPLICATION_JSON)
          .content(new ObjectMapper().writeValueAsString(user))
      )
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.name").value(user.getName()))
      .andExpect(jsonPath("$.userName").value(user.getUserName()));
  }

  @Test
  void testGetDeckById() throws Exception {
    User user = new User(null, "Guilherme", "te4ste");
    this.userService.addUser(user);
    mockMvc
      .perform(get("/user/" + user.getId()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name").value(user.getName()))
      .andExpect(jsonPath("$.userName").value(user.getUserName()));
  }

  @Test
  void testUserNotFound() throws Exception {
    mockMvc
      .perform(get("/user/50"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message").value("User Not Found"));
  }

  
  @Test
  void testDeleteUserNotFound() throws Exception {
    mockMvc
      .perform(get("/user/50"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message").value("User Not Found"));
  }

  @Test
  void testDeleteCardById() throws Exception {
    User user = new User(null, "Guilherme", "te4ste");
    this.userService.addUser(user);
    mockMvc
      .perform(delete("/user/" + user.getId()))
      .andExpect(status().isAccepted())
      .andExpect(jsonPath("$.message").value("User with id " + String.valueOf(user.getId())  + " deleted"));
  }

  @Test
  void testAddNewDeck() throws Exception {
    User user = new User(null, "Guilherme", "te4ste");
    Deck deck = new Deck(null, "Best deck");
    this.userService.addUser(user);

    mockMvc
      .perform(post("/user/" + user.getId() + "/decks").contentType(MediaType.APPLICATION_JSON)
          .content(new ObjectMapper().writeValueAsString(deck))
      )
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.name").value(user.getName()))
      .andExpect(jsonPath("$.userName").value(user.getUserName()))
      .andExpect(jsonPath("$.decks[0].deckName").value(deck.getDeckName()));
  }
}
