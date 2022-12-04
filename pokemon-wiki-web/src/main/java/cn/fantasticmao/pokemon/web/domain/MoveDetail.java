package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * MoveDetail
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
@Entity
@Table(name = "pw_move_detail")
public class MoveDetail extends AbstractDomain<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
