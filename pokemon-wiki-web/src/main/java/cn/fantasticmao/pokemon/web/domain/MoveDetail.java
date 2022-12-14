package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * MoveDetail
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
@Table("t_move_detail")
public class MoveDetail extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String desc;
    private String imgUrl;
    private String notes;
    private String scope;
    private String effect;

    @Override
    public String toString() {
        return "MoveDetail{" +
            "nameZh='" + nameZh + '\'' +
            "} " + super.toString();
    }
}
