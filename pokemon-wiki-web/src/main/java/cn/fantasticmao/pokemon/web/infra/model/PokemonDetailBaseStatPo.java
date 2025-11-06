package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonDetailBaseStatPo
 *
 * @author fantasticmao
 * @since 2018/8/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_pokemon_detail_base_stat")
public class PokemonDetailBaseStatPo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private Integer idx;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer spAttack;
    private Integer spDefense;
    private Integer speed;
    private Integer total;
    private Float average;
}
