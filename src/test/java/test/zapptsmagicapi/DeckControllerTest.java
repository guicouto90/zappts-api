package test.zapptsmagicapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import test.zapptsmagicapi.entities.Deck;
import test.zapptsmagicapi.repositories.DeckRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DeckControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private DeckRepository deckRepository;

  @Test
  void testGetDeckById() throws Exception {
    Deck deck = new Deck(null, "Best Deck");
    this.deckRepository.save(deck);

    mockMvc
      .perform(get("/decks/" + deck.getId()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.deckName").value(deck.getDeckName()));
  }

  @Test
  void testDeckNotFound() throws Exception {
    mockMvc
      .perform(get("/decks/5"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message").value("Deck Not Found"));
  }
}
