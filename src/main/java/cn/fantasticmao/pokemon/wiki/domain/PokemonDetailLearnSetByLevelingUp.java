package cn.fantasticmao.pokemon.wiki.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonDetailLearnSetByLevelingUp
 *
 * @author maomao
 * @since 2019-08-20
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_pokemon_detail_learn_set_by_leveling_up")
public class PokemonDetailLearnSetByLevelingUp extends AbstractDomain<Integer> {
    private int index;
    private String level1;
    private String level2;
    private String move;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
}
