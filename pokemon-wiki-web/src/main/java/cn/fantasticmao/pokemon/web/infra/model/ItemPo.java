package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * ItemPo
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_item")
public class ItemPo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private String type;
    private String imgUrl;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String desc;
    private Integer generation;
}
