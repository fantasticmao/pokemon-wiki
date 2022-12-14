package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Pokemon
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Getter
@Setter
@Table("t_pokemon")
public class Pokemon extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private int idx;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type1;
    private String type2;
    private String form;
    private int generation;

    @Override
    public String toString() {
        return "Pokemon{" +
            "idx=" + idx +
            ", nameZh='" + nameZh + '\'' +
            ", form='" + form + '\'' +
            "} " + super.toString();
    }
}
