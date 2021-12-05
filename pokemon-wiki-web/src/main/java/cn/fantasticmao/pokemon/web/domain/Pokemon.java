package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Pokemon
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_pokemon")
public class Pokemon extends AbstractDomain<Integer> {
    private int index;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type1;
    private String type2;
    private int generation;
}