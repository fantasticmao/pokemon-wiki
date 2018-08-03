package cn.fantasticmao.pokemon.wiki.domain;

import com.mundo.data.domain.AbstractDomain;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonNature
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
@Table(name = "pw_pokemon_nature")
public class PokemonNature extends AbstractDomain<Integer> {
    private int index;
    private String nameZh;
    private String nameJp;
    private String nameEn;
    private String increasedStat;
    private String decreasedStat;
    private String favoriteFlavor;
    private String dislikedFlavor;
}