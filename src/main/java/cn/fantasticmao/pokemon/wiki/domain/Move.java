package cn.fantasticmao.pokemon.wiki.domain;

import cn.fantasticmao.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Move
 *
 * @author maodh
 * @since 2018/8/4
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_move")
public class Move extends AbstractDomain<Integer> {
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
    private int generation;
}