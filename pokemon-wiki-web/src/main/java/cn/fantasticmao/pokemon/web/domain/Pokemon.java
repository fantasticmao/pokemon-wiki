package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Pokemon
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Getter
@Setter
@Entity
@Table(name = "pw_pokemon")
public class Pokemon extends AbstractDomain<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int idx;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type1;
    private String type2;
    private String form;
    private int generation;
}
