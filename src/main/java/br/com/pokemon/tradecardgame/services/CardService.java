package br.com.pokemon.tradecardgame.services;


import br.com.pokemon.tradecardgame.entities.Card;
import br.com.pokemon.tradecardgame.entities.Deck;
import br.com.pokemon.tradecardgame.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.*;

@Service
public class CardService {

    private static final String PROXY_SERVER_HOST = "proxy.tcs.com";
    private static final int PROXY_SERVER_PORT = 8080;
    private static final  String URL_BASE = "https://api.pokemontcg.io/v1";
    private static final Logger LOGGER = LoggerFactory.getLogger(CardService.class);

    private Utils utils;
    private HashMap<String, List<String>> mapHeaders;
    private RestTemplate restTemplate;

    @PostConstruct
    public void initialize() {
        utils = new Utils();
        mapHeaders = new HashMap<>();
        mapHeaders.put("Accept", Arrays.asList(MediaType.APPLICATION_JSON.toString()));
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_SERVER_HOST, PROXY_SERVER_PORT));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //requestFactory.setProxy(proxy);
        restTemplate = new RestTemplate(requestFactory);
    }

    public List<Card> findAll() {
        List<Card> cards = new ArrayList<>();
        try {
            final String URI = String.format("%s/cards", URL_BASE);
            ResponseEntity<Deck> results = restTemplate.getForEntity(URI, Deck.class);
            if(results.getStatusCode().is2xxSuccessful()) {
                cards = results.getBody().getCards();
               // cards = Arrays.asList(results.getBody());
            } else if( results.getStatusCode().is5xxServerError()) {
                throw new Exception("500 - Erro Interno");
            }
        } catch (UnknownHostException e) {
            LOGGER.error(e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cards;
    }

    public Card findById(String id) {
        Card result = new Card();
        try {
            final String URI = String.format("%s/cards/{id}", URL_BASE);
            ResponseEntity<Card[]> results = restTemplate.getForEntity(URL_BASE, Card[].class, id);
            if(results.getStatusCode().is2xxSuccessful()) {
                List<Card> cards = new ArrayList<>();
                cards = Arrays.asList(results.getBody());
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
