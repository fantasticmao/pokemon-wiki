package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Nature
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Getter
@Setter
@Entity
@Table(name = "pw_nature")
public class Nature extends AbstractDomain<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String increasedStat;
    private String decreasedStat;
    private String favoriteFlavor;
    private String dislikedFlavor;
}
