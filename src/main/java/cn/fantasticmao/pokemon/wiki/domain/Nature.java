package cn.fantasticmao.pokemon.wiki.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Nature
 *
 * @author maodh
 * @since 2018/8/4
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_nature")
public class Nature extends AbstractDomain<Integer> {
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String increasedStat;
    private String decreasedStat;
    private String favoriteFlavor;
    private String dislikedFlavor;
}