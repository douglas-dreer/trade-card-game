package br.com.pokemon.tradecardgame.services;

import br.com.pokemon.tradecardgame.entities.Card;
import br.com.pokemon.tradecardgame.entities.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@EnableAutoConfiguration
@SpringBootTest
public class CardServiceTest {
    @InjectMocks
    private CardService service;

    @Mock
    MockHttpServletRequest request = new MockHttpServletRequest();

    @Mock
    RestTemplate restTemplate;

    private final String BASE_URI = "/v1/pokemon/tcg/cards";

    private static Deck deck;
    private static Card card;

    @BeforeEach
    void setup() {
        deck = new Deck();
        card = new Card();
        card.setId("xy7-10");
        deck.setCards(Arrays.asList(card));
    }

    @Test
    public void mustReturnsSuccess_WhenList() {
//        String uri = "/v1/pokemon/tcg/cards";
//
//        ResponseEntity<Deck> responseEntity = ResponseEntity.ok(deck);
//        Mockito.when(restTemplate.getForEntity(uri, Deck.class)).thenReturn(responseEntity);
//
//        Mockito.when(service.findAll()).thenReturn(deck.getCards());
//
//        Assertions.assertEquals(1, deck.getCards().size());
    }

}
