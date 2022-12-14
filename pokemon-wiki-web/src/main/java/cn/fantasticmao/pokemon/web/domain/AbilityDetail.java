package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * AbilityDetail
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
@Table("t_ability_detail")
public class AbilityDetail extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String desc;
    private String effect;
    private String pokemons;

    @Override
    public String toString() {
        return "AbilityDetail{" +
            "nameZh='" + nameZh + '\'' +
            "} " + super.toString();
    }
}
