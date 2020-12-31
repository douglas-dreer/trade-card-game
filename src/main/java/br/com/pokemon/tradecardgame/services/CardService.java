package br.com.pokemon.tradecardgame.services;


import br.com.pokemon.tradecardgame.entities.Card;
import br.com.pokemon.tradecardgame.entities.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private static final  String URL_BASE = "https://api.pokemontcg.io/v1";
    private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

    private RestTemplate restTemplate;

    @PostConstruct
    public void initialize() {
        restTemplate = new RestTemplate();
    }

    public List<Card> findAll(Integer pagina, Integer quantidade) {
        List<Card> cards = new ArrayList<>();
        ResponseEntity<Deck> results = null;
        try {
            final String URI = String.format("%s/cards?page=%s&page&&pageSize=%s", URL_BASE, pagina, quantidade);
            results = restTemplate.getForEntity(URI, Deck.class);

            if(!results.getBody().getCards().isEmpty()) {
                cards = results.getBody().getCards();
            }

        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return cards;
    }

    public Card findById(String id) {
        Card result = new Card();
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String URI = String.format("%s/cards/{id}", URL_BASE);
            ResponseEntity<Card[]> results = restTemplate.getForEntity(URI, Card[].class, id);
            if(results.getStatusCode().is2xxSuccessful()) {
                List<Card> cards = Arrays.asList(results.getBody());
                Optional<Card> card = cards.stream().filter(item -> id.equals(item.getId())).findFirst();
                result = !card.isEmpty() ? card.get() : null;
            } else if( results.getStatusCode().is5xxServerError()) {
                throw new Exception("500 - Erro Interno");
            }
        }catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return result;
    }


    public List<Card> findByName(String name) {
        List<Card> cards = new ArrayList<>();
        try {
            final String URI = String.format("%s/cards?name={name}/", URL_BASE);
            ResponseEntity<Card[]> results = restTemplate.getForEntity(URL_BASE, Card[].class, name);
            if(results.getStatusCode().is2xxSuccessful()) {
                cards = Arrays.asList(results.getBody());
            } else if( results.getStatusCode().is5xxServerError()) {
                throw new Exception("500 - Erro Interno");
            } else {
                throw new Exception("404 - Não Encontrado");
            }
        } catch (Exception e) {
           LOGGER.error(e.getLocalizedMessage());
        }
        return cards;
    }
}
