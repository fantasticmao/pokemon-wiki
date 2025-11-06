package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * NaturePo
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_nature")
public class NaturePo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String increasedStat;
    private String decreasedStat;
    private String favoriteFlavor;
    private String dislikedFlavor;
}
