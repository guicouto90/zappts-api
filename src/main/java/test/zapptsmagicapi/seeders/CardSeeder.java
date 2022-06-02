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
    Card card1 = new Card(null, "Black Lotus", "Limited", "english", true, 95000);
    Card card2 = new Card(null, "Time Walk ", "Limited", "english", true, 95500);
    Card card3 = new Card(null, "Ancestral Recall", "Limited", "english", true, 75000);
    Card card4 = new Card(null, "Underground Sea", "Limited", "english", true, 75000);
    Card card5 = new Card(null, "Mox Jet", "Standart", "portuguese", true, 69000);
    Card card6 = new Card(null, "Timetwister", "Standart", "portuguese", true, 68000);
    Card card7 = new Card(null, "Mox Sapphire", "Standart", "portuguese", true, 60500);
    Card card8 = new Card(null, "Intuition", "Standart", "japanese", true, 59500);
    Card card9 = new Card(null, "Mox Ruby", "Standart", "japanese", true, 57500);
    Card card10 = new Card(null, "Emrakul, o Fim Prometido", "Standart", "portuguese", true, 55000);
    Card card11 = new Card(null, "Coptero do Contrabandista", "Standart", "portuguese", true, 40000);
    Card card12 = new Card(null, "Maravilha do Sistema Eteraulico", "Standart", "portuguese", true, 30000);
    Card card13 = new Card(null, "Gideon, Aliado de Zendikar", "Standart", "japanese", true, 25000);
    Card card14 = new Card(null, "Ishkanah, Viuva-do-cemiterio", "Limited", "japanese", true, 20000);
    Card card15 = new Card(null, "Inspetor de Thraben", "Limited", "japanese", true, 15000);
    Card card16 = new Card(null, "Mecanotita Torrencial", "Limited", "english", true, 10000);
    ArrayList<Card> cards = new ArrayList<Card>(List.of(card1,card2,card3,card4,card5,card6,card7,card8,card9,card10, card11, card12, card13, card14, card15, card16));
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
