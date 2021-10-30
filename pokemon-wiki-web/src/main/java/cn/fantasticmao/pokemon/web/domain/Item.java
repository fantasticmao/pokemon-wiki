package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Item
 *
 * @author maomao
 * @since 2019-03-23
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_item")
public class Item extends AbstractDomain<Integer> {
    private String type;
    private String imgUrl;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String desc;
    private int generation;
}
