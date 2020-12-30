package br.com.pokemon.tradecardgame.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Attack {
    private String name;
    private List<String> cost;
    private String convertedEnergyCost;
    private String damage;
    private String text;

}
