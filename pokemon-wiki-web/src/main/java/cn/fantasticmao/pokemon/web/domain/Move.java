package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Move
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Getter
@Setter
@Table("t_move")
public class Move extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
    private int generation;

    @Override
    public String toString() {
        return "Move{" +
            "nameZh='" + nameZh + '\'' +
            "} " + super.toString();
    }
}
