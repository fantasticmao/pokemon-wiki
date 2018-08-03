package cn.fantasticmao.pokemon.wiki.domain;

import com.mundo.data.domain.AbstractDomain;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonAbility
 *
 * @author maodh
 * @since 2018/8/4
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pw_pokemon_ability")
public class PokemonAbility extends AbstractDomain<Integer> {
    private int index;
    private String nameZh;
    private String type1;
    private String type2;
    private String ability1;
    private String ability2;
    private String abilityHide;
    private int generation;
}