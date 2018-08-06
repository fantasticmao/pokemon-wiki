package cn.fantasticmao.pokemon.wiki.domain;

import com.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Pokemon
 *
 * @author maodh
 * @since 2018/7/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_pokemon")
public class Pokemon extends AbstractDomain<Integer> implements Comparable<Pokemon> {
    private int index;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type1;
    private String type2;
    private int generation;

    @Override
    public int compareTo(@Nonnull Pokemon pokemon) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), pokemon.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getIndex(), pokemon.getIndex())) != 0) {
            return r;
        } else {
            return Integer.compare(this.getId(), pokemon.getId());
        }
    }
}