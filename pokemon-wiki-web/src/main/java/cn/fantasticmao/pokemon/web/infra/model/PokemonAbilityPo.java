package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonAbilityPo
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_pokemon_ability")
public class PokemonAbilityPo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private Integer idx;
    private String nameZh;
    private String type1;
    private String type2;
    private String ability1;
    private String ability2;
    private String abilityHide;
    private String form;
    private Integer generation;
}
