package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import cn.fantasticmao.pokemon.wiki.domain.PokemonAbility;
import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import cn.fantasticmao.pokemon.wiki.domain.PokemonDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * PokemonBean
 *
 * @author maodh
 * @since 2018/8/29
 */
@Getter
@ToString
public class PokemonBean implements Serializable, Comparable<PokemonBean> {
    private final int index;
    private final String nameZh;
    private final String nameJa;
    private final String nameEn;
    private final String type1;
    private final String type2;
    private final String ability1;
    private final String ability2;
    private final String abilityHide;
    private final int generation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Detail detail;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final BaseStat baseStat;

    @Override
    public int compareTo(@Nonnull PokemonBean pokemonBean) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), pokemonBean.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getIndex(), pokemonBean.getIndex())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    public PokemonBean(Pokemon pokemon, PokemonAbility pokemonAbility) {
        this(pokemon, pokemonAbility, null, null);
    }

    public PokemonBean(Pokemon pokemon, PokemonAbility pokemonAbility, PokemonDetail pokemonDetail, PokemonBaseStat pokemonBaseStat) {
        this.index = pokemon.getIndex();
        this.nameZh = pokemon.getNameZh();
        this.nameJa = pokemon.getNameJa();
        this.nameEn = pokemon.getNameEn();
        this.type1 = pokemonAbility.getType1();
        this.type2 = pokemonAbility.getType2();
        this.ability1 = pokemonAbility.getAbility1();
        this.ability2 = pokemonAbility.getAbility2();
        this.abilityHide = pokemonAbility.getAbilityHide();
        this.generation = pokemon.getGeneration();
        this.detail = pokemonDetail == null ? null : Detail.ofDomain(pokemonDetail);
        this.baseStat = pokemonBaseStat == null ? null : BaseStat.ofDomain(pokemonBaseStat);
    }

    /**
     * 详细信息
     */
    @Getter
    @ToString
    private static class Detail implements Serializable {
        private final String imgUrl;
        private final String category;
        private final String height;
        private final String weight;
        private final String bodyStyle;
        private final String catchRate;
        private final String genderRatio;
        private final String eggGroup1;
        private final String eggGroup2;
        private final String hatchTime;
        private final String effortValue;

        private Detail(String imgUrl, String category, String height, String weight, String bodyStyle, String catchRate,
                       String genderRatio, String eggGroup1, String eggGroup2, String hatchTime, String effortValue) {
            this.imgUrl = imgUrl;
            this.category = category;
            this.height = height;
            this.weight = weight;
            this.bodyStyle = bodyStyle;
            this.catchRate = catchRate;
            this.genderRatio = genderRatio;
            this.eggGroup1 = eggGroup1;
            this.eggGroup2 = eggGroup2;
            this.hatchTime = hatchTime;
            this.effortValue = effortValue;
        }

        private static Detail ofDomain(PokemonDetail pokemonDetail) {
            return new Detail(pokemonDetail.getImgUrl(), pokemonDetail.getCategory(), pokemonDetail.getHeight(),
                    pokemonDetail.getWeight(), pokemonDetail.getBodyStyle(), pokemonDetail.getCatchRate(),
                    pokemonDetail.getGenderRatio(), pokemonDetail.getEggGroup1(), pokemonDetail.getEggGroup2(),
                    pokemonDetail.getHatchTime(), pokemonDetail.getEffortValue());
        }
    }

    /**
     * 种族值
     */
    @Getter
    @ToString
    private static class BaseStat implements Serializable {
        private final int hp;
        private final int attack;
        private final int defense;
        private final int spAttack;
        private final int spDefense;
        private final int speed;
        private final int total;
        private final float average;

        private BaseStat(int hp, int attack, int defense, int spAttack, int spDefense, int speed, int total, float average) {
            this.hp = hp;
            this.attack = attack;
            this.defense = defense;
            this.spAttack = spAttack;
            this.spDefense = spDefense;
            this.speed = speed;
            this.total = total;
            this.average = average;
        }

        private static BaseStat ofDomain(PokemonBaseStat pokemonBaseStat) {
            return new BaseStat(pokemonBaseStat.getHp(), pokemonBaseStat.getAttack(), pokemonBaseStat.getDefense(),
                    pokemonBaseStat.getSpAttack(), pokemonBaseStat.getSpDefense(), pokemonBaseStat.getSpeed(),
                    pokemonBaseStat.getTotal(), pokemonBaseStat.getAverage());
        }
    }
}
