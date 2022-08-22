package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Move
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Getter
@Setter
@Entity
@Table(name = "pw_move")
public class Move extends AbstractDomain<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
