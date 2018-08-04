package cn.fantasticmao.pokemon.wiki.domain;

import com.mundo.data.domain.AbstractDomain;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonMove
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
@Table(name = "pw_pokemon_move")
public class PokemonMove extends AbstractDomain<Integer> {
    private int index;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
    private int generation;
}