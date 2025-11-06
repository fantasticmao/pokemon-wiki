package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonPo
 *
 * @author fantasticmao
 * @since 2018/7/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_pokemon")
public class PokemonPo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private Integer idx;
    private String nameZh;
    private String nameJa;
    private String nameEn;
    private String type1;
    private String type2;
    private String form;
    private Integer generation;
}
