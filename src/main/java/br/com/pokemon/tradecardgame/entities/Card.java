package br.com.pokemon.tradecardgame.entities;

import br.com.pokemon.tradecardgame.pojos.Ability;
import br.com.pokemon.tradecardgame.pojos.Attack;
import br.com.pokemon.tradecardgame.pojos.Weakness;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {
    private String id;
    private String name;
    private String imageUrl;
    private String subtype;
    private String supertype;
    private String evolvesFrom;
    private Ability ability;
    private String hp;
    private List<String> retreatCost;
    private String convertedRetreatCost;
    private String number;
    private String artist;
    private String rarity;
    private String series;
    private String set;
    private String setCode;
    private List<String> types;
    private List<Attack> Attacks;
    private List<Weakness> weaknesses;
    private String imageUrlHiRes;
    private String nationalPokedexNumber;
}


