package cn.fantasticmao.pokemon.wiki.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonBaseStat
 *
 * @author maodh
 * @since 2018/8/4
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_pokemon_base_stat")
public class PokemonBaseStat extends AbstractDomain<Integer> {
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