package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.mundo.data.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PokemonDetail
 *
 * @author maodh
 * @since 2018/8/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "pw_pokemon_detail")
public class PokemonDetail extends AbstractDomain<Integer> {
    private int index;
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
