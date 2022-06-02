package test.zapptsmagicapi.services;

/* import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays; */
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
// import com.google.api.client.http.HttpTransport;
// import com.google.api.client.json.JsonFactory;
// import com.google.api.client.json.jackson2.JacksonFactory;
// import com.google.api.services.translate.Translate;
// import com.google.api.services.translate.TranslateRequest;
// import com.google.api.services.translate.TranslateRequestInitializer;

import test.zapptsmagicapi.entities.Card;
import test.zapptsmagicapi.exceptions.ExceptionNotFound;
// import test.zapptsmagicapi.exceptions.InternalErrorException;
import test.zapptsmagicapi.exceptions.ExceptionBadRequest;
import test.zapptsmagicapi.repositories.CardRepository;

/* import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate; */ 

@Service
public class CardService {
  
  @Autowired
  private CardRepository cardRepository;

  public void verifyLanguage(String language) {
    if(!language.equals("portuguese") && !language.equals("english") && !language.equals("japanese")) {
      throw new ExceptionBadRequest("Language field must be filled with portuguese, english or japanese");
    }
  }

  /*public String translateCardName(String cardName) {
    TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer(
      "Generated key from google console");

    // Set up the HTTP transport and JSON factory
    HttpTransport httpTransport;
    try {
      httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    // set up translate
    final Translate translate = new Translate.Builder(httpTransport, jsonFactory, null)
      .setApplicationName("My Apps").setTranslateRequestInitializer(translateRequestInitializer).build();

    List<String> sourceTextList = Arrays.asList("source Text");
    // translate
    System.out.println(translate.translations().list(sourceTextList, "pt").execute());
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return cardName;
  } */ 

  public Card addCard(Card card) {
    this.verifyLanguage(card.getLanguage());
    // this.translateCardName(card.getName());
    return this.cardRepository.save(card);
  }

  public List<Card> listAllCards() {
    return this.cardRepository.findAll();
  }

  public Card listCardById(Integer id) {
    try {
      return this.cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Card Not Found"));
    } catch(NoSuchElementException e) {
      throw new ExceptionNotFound("Card not found");
    }
  }

  public void removeCard(Integer id) {
    this.listCardById(id);
    this.cardRepository.deleteById(id);
  }
}
