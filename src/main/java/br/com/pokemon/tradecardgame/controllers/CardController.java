package br.com.pokemon.tradecardgame.controllers;

import br.com.pokemon.tradecardgame.entities.Card;
import br.com.pokemon.tradecardgame.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pokemon/tcg")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping(path = "cards", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Card>> list() {
        List<Card> result = service.findAll();
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> findByName(@RequestParam(name = "name", required = true) String name) {
        List<Card> result = service.findByName(name);
        return !result.isEmpty() ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @ResponseBody
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> findById(@PathVariable("id") String id) {
        Card result = service.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }




}
