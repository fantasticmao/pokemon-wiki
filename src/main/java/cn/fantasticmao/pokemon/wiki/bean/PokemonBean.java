package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PokemonBean
 *
 * @author maodh
 * @since 2018/8/29
 */
@Getter
@ToString
public class PokemonBean implements Serializable, Comparable<PokemonBean> {
    /**
     * 全国图鉴
     */
    private final int index;

    /**
     * 中文名称
     */
    private final String nameZh;

    /**
     * 日文名称
     */
    private final String nameJa;

    /**
     * 英文名称
     */
    private final String nameEn;

    /**
     * 属性1
     */
    private final String type1;

    /**
     * 属性2
     */
    private final String type2;

    /**
     * 特性1
     */
    private final String ability1;

    /**
     * 特性2
     */
    private final String ability2;

    /**
     * 隐藏特性
     */
    private final String abilityHide;

    /**
     * 第几世代
     */
    private final int generation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final BaseStat baseStat;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Detail detail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<LearnSetByLevelingUp> learnSetByLevelingUp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<LearnSetByTechnicalMachine> learnSetByTechnicalMachine;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<LearnSetByBreeding> learnSetByBreeding;

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
        this(pokemon, pokemonAbility, null, null, null, null, null);
    }

    public PokemonBean(Pokemon pokemon, PokemonAbility pokemonAbility, PokemonDetailBaseStat pokemonDetailBaseStat, PokemonDetail pokemonDetail,
                       List<PokemonDetailLearnSetByLevelingUp> pokemonDetailLearnSetByLevelingUp,
                       List<PokemonDetailLearnSetByTechnicalMachine> pokemonDetailLearnSetByTechnicalMachine,
                       List<PokemonDetailLearnSetByBreeding> pokemonDetailLearnSetByBreeding) {
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
        this.baseStat = pokemonDetailBaseStat == null ? null : BaseStat.ofDomain(pokemonDetailBaseStat);
        this.detail = pokemonDetail == null ? null : Detail.ofDomain(pokemonDetail);
        this.learnSetByLevelingUp = CollectionUtils.isEmpty(pokemonDetailLearnSetByLevelingUp)
            ? null : pokemonDetailLearnSetByLevelingUp.stream().map(LearnSetByLevelingUp::ofDomain).collect(Collectors.toList());
        this.learnSetByTechnicalMachine = CollectionUtils.isEmpty(pokemonDetailLearnSetByTechnicalMachine)
            ? null : pokemonDetailLearnSetByTechnicalMachine.stream().map(LearnSetByTechnicalMachine::ofDomain).collect(Collectors.toList());
        this.learnSetByBreeding = CollectionUtils.isEmpty(pokemonDetailLearnSetByBreeding)
            ? null : pokemonDetailLearnSetByBreeding.stream().map(LearnSetByBreeding::ofDomain).collect(Collectors.toList());
    }

    /**
     * 种族值
     */
    @Getter
    @ToString
    private static class BaseStat implements Serializable {
        /**
         * HP
         */
        private final int hp;

        /**
         * 攻击
         */
        private final int attack;

        /**
         * 防御
         */
        private final int defense;

        /**
         * 特攻
         */
        private final int spAttack;

        /**
         * 特防
         */
        private final int spDefense;

        /**
         * 速度
         */
        private final int speed;

        /**
         * 总合
         */
        private final int total;

        /**
         * 平均值
         */
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

        private static BaseStat ofDomain(PokemonDetailBaseStat pokemonBaseStat) {
            return new BaseStat(pokemonBaseStat.getHp(), pokemonBaseStat.getAttack(), pokemonBaseStat.getDefense(),
                pokemonBaseStat.getSpAttack(), pokemonBaseStat.getSpDefense(), pokemonBaseStat.getSpeed(),
                pokemonBaseStat.getTotal(), pokemonBaseStat.getAverage());
        }
    }

    /**
     * 详细信息
     */
    @Getter
    @ToString
    private static class Detail implements Serializable {
        /**
         * 预览图片
         */
        private final String imgUrl;

        /**
         * 分类
         */
        private final String category;

        /**
         * 身高
         */
        private final String height;

        /**
         * 体重
         */
        private final String weight;

        /**
         * 体型
         */
        private final String bodyStyle;

        /**
         * 捕获率
         */
        private final String catchRate;

        /**
         * 性别比例，以逗号分隔
         */
        private final String genderRatio;

        /**
         * 蛋组1
         */
        private final String eggGroup1;

        /**
         * 蛋组2
         */
        private final String eggGroup2;

        /**
         * 孵化时间
         */
        private final String hatchTime;

        /**
         * 基础点数，以逗号分隔
         */
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

    @Getter
    @ToString
    private static class LearnSetByLevelingUp implements Serializable {
        /**
         * 等级（太阳/月亮）
         */
        private final String level1;

        /**
         * 等级（究极之日/究极之月）
         */
        private final String level2;

        /**
         * 招式名称
         */
        private final String move;

        /**
         * 属性
         */
        private final String type;

        /**
         * 分类
         */
        private final String category;

        /**
         * 威力
         */
        private final String power;

        /**
         * 命中
         */
        private final String accuracy;

        /**
         * PP
         */
        private final String pp;

        private LearnSetByLevelingUp(String level1, String level2, String move, String type, String category, String power, String accuracy, String pp) {
            this.level1 = level1;
            this.level2 = level2;
            this.move = move;
            this.type = type;
            this.category = category;
            this.power = power;
            this.accuracy = accuracy;
            this.pp = pp;
        }

        private static LearnSetByLevelingUp ofDomain(PokemonDetailLearnSetByLevelingUp pokemonDetailLearnSetByLevelingUp) {
            return new LearnSetByLevelingUp(pokemonDetailLearnSetByLevelingUp.getLevel1(), pokemonDetailLearnSetByLevelingUp.getLevel2(),
                pokemonDetailLearnSetByLevelingUp.getMove(), pokemonDetailLearnSetByLevelingUp.getType(), pokemonDetailLearnSetByLevelingUp.getCategory(),
                pokemonDetailLearnSetByLevelingUp.getPower(), pokemonDetailLearnSetByLevelingUp.getAccuracy(), pokemonDetailLearnSetByLevelingUp.getPp());
        }
    }

    @Getter
    @ToString
    private static class LearnSetByTechnicalMachine implements Serializable {
        /**
         * 招式学习器图片链接
         */
        private final String imgUrl;

        /**
         * 招式学习器名称
         */
        private final String technicalMachine;

        /**
         * 招式名称
         */
        private final String move;

        /**
         * 属性
         */
        private final String type;

        /**
         * 分类
         */
        private final String category;

        /**
         * 威力
         */
        private final String power;

        /**
         * 命中
         */
        private final String accuracy;

        /**
         * PP
         */
        private final String pp;

        private LearnSetByTechnicalMachine(String imgUrl, String technicalMachine, String move, String type, String category, String power, String accuracy, String pp) {
            this.imgUrl = imgUrl;
            this.technicalMachine = technicalMachine;
            this.move = move;
            this.type = type;
            this.category = category;
            this.power = power;
            this.accuracy = accuracy;
            this.pp = pp;
        }

        private static LearnSetByTechnicalMachine ofDomain(PokemonDetailLearnSetByTechnicalMachine pokemonDetailLearnSetByTechnicalMachine) {
            return new LearnSetByTechnicalMachine(pokemonDetailLearnSetByTechnicalMachine.getImgUrl(), pokemonDetailLearnSetByTechnicalMachine.getTechnicalMachine(),
                pokemonDetailLearnSetByTechnicalMachine.getMove(), pokemonDetailLearnSetByTechnicalMachine.getType(),
                pokemonDetailLearnSetByTechnicalMachine.getCategory(), pokemonDetailLearnSetByTechnicalMachine.getPower(),
                pokemonDetailLearnSetByTechnicalMachine.getAccuracy(), pokemonDetailLearnSetByTechnicalMachine.getPp());
        }
    }

    @Getter
    @ToString
    private static class LearnSetByBreeding implements Serializable {
        /**
         * 亲代
         */
        private final String parent;

        /**
         * 招式名称
         */
        private final String move;

        /**
         * 属性
         */
        private final String type;

        /**
         * 分类
         */
        private final String category;

        /**
         * 威力
         */
        private final String power;

        /**
         * 命中
         */
        private final String accuracy;

        /**
         * PP
         */
        private final String pp;

        private LearnSetByBreeding(String parent, String move, String type, String category, String power, String accuracy, String pp) {
            this.parent = parent;
            this.move = move;
            this.type = type;
            this.category = category;
            this.power = power;
            this.accuracy = accuracy;
            this.pp = pp;
        }

        private static LearnSetByBreeding ofDomain(PokemonDetailLearnSetByBreeding pokemonDetailLearnSetByBreeding) {
            return new LearnSetByBreeding(pokemonDetailLearnSetByBreeding.getParent(), pokemonDetailLearnSetByBreeding.getMove(),
                pokemonDetailLearnSetByBreeding.getType(), pokemonDetailLearnSetByBreeding.getCategory(),
                pokemonDetailLearnSetByBreeding.getPower(), pokemonDetailLearnSetByBreeding.getAccuracy(),
                pokemonDetailLearnSetByBreeding.getPp());
        }
    }

}
