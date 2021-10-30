package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MoveDetail
 *
 * @author maodh
 * @since 2018/8/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_move_detail")
public class MoveDetail extends AbstractDomain<Integer> {
    private String nameZh;
    private String desc;
    private String imgUrl;
    private String notes;
    private String scope;
    private String effect;
}
