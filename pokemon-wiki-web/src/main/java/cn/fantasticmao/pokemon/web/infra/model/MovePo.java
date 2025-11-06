package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * MovePo
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_move")
public class MovePo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
    private Integer generation;
}
