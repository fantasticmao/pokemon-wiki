package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * PokemonAbility
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Getter
@Setter
@Entity
@Table(name = "pw_pokemon_ability")
public class PokemonAbility extends AbstractDomain<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int idx;
    private String nameZh;
    private String type1;
    private String type2;
    private String ability1;
    private String ability2;
    private String abilityHide;
    private String form;
    private int generation;
}
