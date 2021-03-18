package cn.fantasticmao.pokemon.wiki.domain;

import cn.fantasticmao.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonDetailBaseStat
 *
 * @author maodh
 * @since 2018/8/4
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_pokemon_detail_base_stat")
public class PokemonDetailBaseStat extends AbstractDomain<Integer> {
    private int index;
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;
    private int total;
    private float average;
}