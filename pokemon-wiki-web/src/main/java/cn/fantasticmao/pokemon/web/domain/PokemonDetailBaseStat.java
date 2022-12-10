package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonDetailBaseStat
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Getter
@Setter
@Table("t_pokemon_detail_base_stat")
public class PokemonDetailBaseStat extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private int idx;
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;
    private int total;
    private float average;

    @Override
    public String toString() {
        return "PokemonDetailBaseStat{" +
            "idx=" + idx +
            ", total=" + total +
            "} " + super.toString();
    }
}
