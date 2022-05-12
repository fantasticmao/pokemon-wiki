package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonDetailLearnSetByTechnicalMachine
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
@Getter
@Setter
@Entity
@Table(name = "pw_pokemon_detail_learn_set_by_technical_machine")
public class PokemonDetailLearnSetByTechnicalMachine extends AbstractDomain<Integer> {
    private int index;
    private String imgUrl;
    private String technicalMachine;
    private String move;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
}
