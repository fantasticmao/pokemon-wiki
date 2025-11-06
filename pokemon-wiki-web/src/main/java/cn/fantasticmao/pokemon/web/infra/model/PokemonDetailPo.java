package cn.fantasticmao.pokemon.web.infra.model;

import cn.fantasticmao.mundo.data.jdbc.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonDetailPo
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("t_pokemon_detail")
public class PokemonDetailPo extends AbstractEntity<Integer> {
    @Id
    private Integer id;
    private Integer idx;
    private String imgUrl;
    private String type;
    private String category;
    private String ability;
    private String height;
    private String weight;
    private String bodyStyle;
    private String catchRate;
    private String genderRatio;
    private String eggGroup1;
    private String eggGroup2;
    private String hatchTime;
    private String effortValue;
}
