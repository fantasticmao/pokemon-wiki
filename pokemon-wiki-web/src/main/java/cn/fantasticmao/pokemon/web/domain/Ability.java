package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Ability
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
@Table("t_ability")
public class Ability extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String effect;
    private int generation;

    @Override
    public String toString() {
        return "Ability{" +
            "nameZh='" + nameZh + '\'' +
            "} " + super.toString();
    }
}
