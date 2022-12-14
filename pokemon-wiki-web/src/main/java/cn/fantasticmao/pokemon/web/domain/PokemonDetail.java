package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.jdbc.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * PokemonDetail
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
@Table("t_pokemon_detail")
public class PokemonDetail extends AbstractDomain<Integer> {
    @Id
    private Integer id;
    private int idx;
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

    @Override
    public String toString() {
        return "PokemonDetail{" +
            "idx=" + idx +
            ", eggGroup1='" + eggGroup1 + '\'' +
            ", eggGroup2='" + eggGroup2 + '\'' +
            "} " + super.toString();
    }
}
