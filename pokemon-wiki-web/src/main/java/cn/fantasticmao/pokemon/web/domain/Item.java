package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Item
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Getter
@Setter
@Table("t_item")
public class Item extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private String type;
    private String imgUrl;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String desc;
    private int generation;

    @Override
    public String toString() {
        return "Item{" +
            "type='" + type + '\'' +
            ", nameZh='" + nameZh + '\'' +
            "} " + super.toString();
    }
}
