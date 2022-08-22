package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Ability
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
@Entity
@Table(name = "pw_ability")
public class Ability extends AbstractDomain<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String effect;
    private int generation;
}
