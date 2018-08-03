package cn.fantasticmao.pokemon.wiki.domain;

import com.mundo.data.domain.AbstractDomain;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonBaseStats
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
@Table(name = "pw_pokemon_base_stats")
public class PokemonBaseStats extends AbstractDomain<Integer> {
    private int index;
    private String nameZh;
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;
    private int total;
    private float average;
}