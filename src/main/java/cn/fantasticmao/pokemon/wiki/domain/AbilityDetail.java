package cn.fantasticmao.pokemon.wiki.domain;

import com.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AbilityDetail
 *
 * @author maodh
 * @since 2018/8/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_ability_detail")
public class AbilityDetail extends AbstractDomain<Integer> {
    private String nameZh;
    private String desc;
    private String effect;
    private String pokemons;
}
