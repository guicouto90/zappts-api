package test.zapptsmagicapi.seeders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.repositories.CardRepository;
import test.zapptsmagicapi.services.CardService;

@Component
public class CardSeeder implements CommandLineRunner {

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private CardService cardService;

  @Override
  public void run(String... args) throws Exception {
    this.loadCardData();
  }

  public ArrayList<Card> arrayCards() {
    Card card1 = new Card(null, "Black Lotus", "limited", "english", true, 95000);
    Card card2 = new Card(null, "Time Walk ", "limited", "english", true, 95500);
    Card card3 = new Card(null, "Ancestral Recall", "limited", "english", true, 75000);
    Card card4 = new Card(null, "Underground Sea", "limited", "english", true, 75000);
    Card card5 = new Card(null, "Mox Jet", "1500", "portuguese", true, 69000);
    Card card6 = new Card(null, "Timetwister", "1500", "portuguese", true, 68000);
    Card card7 = new Card(null, "Mox Sapphire", "1500", "portuguese", true, 60500);
    Card card8 = new Card(null, "Intuition", "1500", "japanese", true, 59500);
    Card card9 = new Card(null, "Mox Ruby", "limited", "japanese", true, 57500);
    Card card10 = new Card(null, "Mox Pearl", "limited", "japanese", true, 55000);
    ArrayList<Card> cards = new ArrayList<Card>(List.of(card1,card2,card3,card4,card5,card6,card7,card8,card9,card10));
    return cards;
  }

  private void loadCardData() {
    if(cardRepository.count() == 0) {
      ArrayList<Card> cards = this.arrayCards();
      for(Card card : cards) {
        this.cardService.addCard(card);
      }

    }
  }
  
}
