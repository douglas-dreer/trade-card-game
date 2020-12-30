package br.com.pokemon.tradecardgame.services;

import br.com.pokemon.tradecardgame.entities.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CardService service;

    @Mock
    MockHttpServletRequest request = new MockHttpServletRequest();

    private final String BASE_URI = "/v1/pokemon/tcg/cards";

    private static Card[] cards;

    @BeforeEach
    void setup() {
        cards = new Card[]{new Card()};
    }
}
