package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * AbilityPo
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_ability")
public class AbilityPo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String effect;
    private Integer generation;
}
