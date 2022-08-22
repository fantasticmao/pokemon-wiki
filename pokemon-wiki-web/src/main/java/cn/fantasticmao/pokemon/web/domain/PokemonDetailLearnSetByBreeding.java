package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * PokemonDetailLearnSetByBreeding
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
@Getter
@Setter
@Entity
@Table(name = "pw_pokemon_detail_learn_set_by_breeding")
public class PokemonDetailLearnSetByBreeding extends AbstractDomain<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int index;
    private String parent;
    private String move;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
}
