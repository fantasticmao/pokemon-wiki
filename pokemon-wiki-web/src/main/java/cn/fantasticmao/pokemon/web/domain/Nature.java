package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Nature
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Getter
@Setter
@Table("t_nature")
public class Nature extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String increasedStat;
    private String decreasedStat;
    private String favoriteFlavor;
    private String dislikedFlavor;

    @Override
    public String toString() {
        return "Nature{" +
            "nameZh='" + nameZh + '\'' +
            "} " + super.toString();
    }
}
