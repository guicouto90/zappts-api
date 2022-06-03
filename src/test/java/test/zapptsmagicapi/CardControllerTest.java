package test.zapptsmagicapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.repositories.CardRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CardRepository cardRepository;

  //@BeforeEach
  //public void setup() {
  //  cardRepository.deleteAll();
  //}
  @Test
  void testOk() throws Exception {
    mockMvc
      .perform(get("/cards"))
      .andExpect(status().isOk());
  }

  @Test
  void TestAddNewCard() throws Exception {
    Card card = new Card(null, "Carta teste", "Teste Edition", "english", false, 2.99);

    mockMvc
      .perform(post("/cards").contentType(MediaType.APPLICATION_JSON)
          .content(new ObjectMapper().writeValueAsString(card))
      )
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.name").value(card.getName()))
      .andExpect(jsonPath("$.edition").value(card.getEdition()))
      .andExpect(jsonPath("$.language").value(card.getLanguage()))
      .andExpect(jsonPath("$.foil").value(card.getFoil()))
      .andExpect(jsonPath("$.price").value(card.getPrice()));
  }

  @Test
  void testGetCardById() throws Exception {
    Card card = new Card(null, "Carta teste", "Teste Edition", "english", false, 2.99);
    this.cardRepository.save(card);
    mockMvc
      .perform(get("/cards/" + card.getId()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name").value(card.getName()))
      .andExpect(jsonPath("$.edition").value(card.getEdition()))
      .andExpect(jsonPath("$.language").value(card.getLanguage()))
      .andExpect(jsonPath("$.foil").value(card.getFoil()))
      .andExpect(jsonPath("$.price").value(card.getPrice()));
  }

  @Test
  void testCardNotFound() throws Exception {
    mockMvc
      .perform(get("/cards/50"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message").value("Card Not Found"));
  }

  @Test
  void testDeleteCardNotFound() throws Exception {
    mockMvc
      .perform(delete("/cards/50"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.message").value("Card Not Found"));
  }

  @Test
  void testDeleteCardById() throws Exception {
    Card card = new Card(null, "Carta teste", "Teste Edition", "english", false, 2.99);
    this.cardRepository.save(card);
    mockMvc
      .perform(delete("/cards/" + card.getId()))
      .andExpect(status().isAccepted())
      .andExpect(jsonPath("$.message").value("Card with id " + String.valueOf(card.getId())  + " deleted"));
  }
}
