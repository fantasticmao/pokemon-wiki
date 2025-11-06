package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonDetailLearnSetByTechnicalMachinePo
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_pokemon_detail_learn_set_by_technical_machine")
public class PokemonDetailLearnSetByTechnicalMachinePo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private Integer idx;
    private String imgUrl;
    private String technicalMachine;
    private String move;
    private String type;
    private String category;
    private String power;
    private String accuracy;
    private String pp;
}
