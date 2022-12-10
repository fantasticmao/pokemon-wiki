package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonAbility
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Getter
@Setter
@Table("t_pokemon_ability")
public class PokemonAbility extends AbstractDomain<Integer> {
    @Id
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

    @Override
    public String toString() {
        return "PokemonAbility{" +
            "idx=" + idx +
            ", nameZh='" + nameZh + '\'' +
            ", form='" + form + '\'' +
            "} " + super.toString();
    }
}
