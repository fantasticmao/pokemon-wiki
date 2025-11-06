package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonDetailLearnSetByLevelingUpPo
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_pokemon_detail_learn_set_by_leveling_up")
public class PokemonDetailLearnSetByLevelingUpPo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private Integer idx;
    private String level;
    private String move;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
}
