package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import cn.fantasticmao.pokemon.wiki.domain.PokemonAbility;
import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import cn.fantasticmao.pokemon.wiki.domain.PokemonDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "全国图鉴", example = "1")
    private final int index;
    @ApiModelProperty(value = "中文名称", example = "妙蛙种子")
    private final String nameZh;
    @ApiModelProperty(value = "日文名称", example = "フシギダネ")
    private final String nameJa;
    @ApiModelProperty(value = "英文名称", example = "Bulbasaur")
    private final String nameEn;
    @ApiModelProperty(value = "属性1", example = "草")
    private final String type1;
    @ApiModelProperty(value = "属性2", example = "毒")
    private final String type2;
    @ApiModelProperty(value = "特性1", example = "茂盛")
    private final String ability1;
    @ApiModelProperty(value = "特性2", example = "")
    private final String ability2;
    @ApiModelProperty(value = "隐藏特性", example = "叶绿素")
    private final String abilityHide;
    @ApiModelProperty(value = "第几世代", example = "1")
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
        @ApiModelProperty(value = "预览图片", example = "https://s1.52poke.wiki/wiki/thumb/2/21/001Bulbasaur.png/300px-001Bulbasaur.png")
        private final String imgUrl;
        @ApiModelProperty(value = "分类", example = "种子宝可梦")
        private final String category;
        @ApiModelProperty(value = "身高", example = "0.7m")
        private final String height;
        @ApiModelProperty(value = "体重", example = "6.9kg")
        private final String weight;
        @ApiModelProperty(value = "体型", example = "https://s1.52poke.wiki/wiki/c/cc/Body08.png")
        private final String bodyStyle;
        @ApiModelProperty(value = "捕获率", example = "5.9%")
        private final String catchRate;
        @ApiModelProperty(value = "性别比例，以逗号分隔", example = "雄性 87.5%,雌性 12.5%")
        private final String genderRatio;
        @ApiModelProperty(value = "蛋组1", example = "怪兽")
        private final String eggGroup1;
        @ApiModelProperty(value = "蛋组2", example = "植物群")
        private final String eggGroup2;
        @ApiModelProperty(value = "孵化时间", example = "5140步")
        private final String hatchTime;
        @ApiModelProperty(value = "基础点数，以逗号分隔", example = "HP 0,攻击 0,防御 0,特攻 1,特防 0,速度 0")
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
        @ApiModelProperty(value = "HP", example = "45")
        private final int hp;
        @ApiModelProperty(value = "攻击", example = "49")
        private final int attack;
        @ApiModelProperty(value = "防御", example = "49")
        private final int defense;
        @ApiModelProperty(value = "特攻", example = "65")
        private final int spAttack;
        @ApiModelProperty(value = "特防", example = "65")
        private final int spDefense;
        @ApiModelProperty(value = "速度", example = "45")
        private final int speed;
        @ApiModelProperty(value = "总合", example = "318")
        private final int total;
        @ApiModelProperty(value = "平均值", example = "53")
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
