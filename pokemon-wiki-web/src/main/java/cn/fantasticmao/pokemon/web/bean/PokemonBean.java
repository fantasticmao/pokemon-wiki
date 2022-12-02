package cn.fantasticmao.pokemon.web.bean;

import cn.fantasticmao.pokemon.web.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PokemonBean
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@Setter
public class PokemonBean implements Comparable<PokemonBean> {
    /**
     * 全国图鉴
     */
    private int index;

    /**
     * 中文名称
     */
    private String nameZh;

    /**
     * 日文名称
     */
    private String nameJa;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 属性1
     */
    private String type1;

    /**
     * 属性2
     */
    private String type2;

    /**
     * 特性1
     */
    private String ability1;

    /**
     * 特性2
     */
    private String ability2;

    /**
     * 隐藏特性
     */
    private String abilityHide;

    /**
     * 形态
     */
    private String form;

    /**
     * 第几世代
     */
    private int generation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BaseStat baseStat;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Detail detail;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LearnSetByLevelingUp> learnSetByLevelingUp;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LearnSetByTechnicalMachine> learnSetByTechnicalMachine;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LearnSetByBreeding> learnSetByBreeding;

    @Override
    public int compareTo(@Nonnull PokemonBean that) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), that.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getIndex(), that.getIndex())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    public PokemonBean() {
    }

    public PokemonBean(@Nonnull Pokemon pokemon, @Nonnull PokemonAbility pokemonAbility) {
        this(pokemon, pokemonAbility, null, null,
            Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    public PokemonBean(@Nonnull Pokemon pokemon, @Nonnull PokemonAbility pokemonAbility,
                       @Nullable PokemonDetailBaseStat pokemonDetailBaseStat, @Nullable PokemonDetail pokemonDetail,
                       @Nonnull List<PokemonDetailLearnSetByLevelingUp> pokemonDetailLearnSetByLevelingUp,
                       @Nonnull List<PokemonDetailLearnSetByTechnicalMachine> pokemonDetailLearnSetByTechnicalMachine,
                       @Nonnull List<PokemonDetailLearnSetByBreeding> pokemonDetailLearnSetByBreeding) {
        this.index = pokemon.getIdx();
        this.nameZh = pokemon.getNameZh();
        this.nameJa = pokemon.getNameJa();
        this.nameEn = pokemon.getNameEn();
        this.type1 = pokemonAbility.getType1();
        this.type2 = pokemonAbility.getType2();
        this.ability1 = pokemonAbility.getAbility1();
        this.ability2 = pokemonAbility.getAbility2();
        this.abilityHide = pokemonAbility.getAbilityHide();
        this.form = pokemon.getForm();
        this.generation = pokemon.getGeneration();
        this.baseStat = BaseStat.ofDomain(pokemonDetailBaseStat);
        this.detail = Detail.ofDomain(pokemonDetail);
        this.learnSetByLevelingUp = pokemonDetailLearnSetByLevelingUp.stream()
            .map(LearnSetByLevelingUp::ofDomain)
            .collect(Collectors.toList());
        this.learnSetByTechnicalMachine = pokemonDetailLearnSetByTechnicalMachine.stream()
            .map(LearnSetByTechnicalMachine::ofDomain)
            .collect(Collectors.toList());
        this.learnSetByBreeding = pokemonDetailLearnSetByBreeding.stream()
            .map(LearnSetByBreeding::ofDomain)
            .collect(Collectors.toList());
    }

    /**
     * 种族值
     */
    @Getter
    @Setter
    public static class BaseStat {
        /**
         * HP
         */
        private int hp;

        /**
         * 攻击
         */
        private int attack;

        /**
         * 防御
         */
        private int defense;

        /**
         * 特攻
         */
        private int spAttack;

        /**
         * 特防
         */
        private int spDefense;

        /**
         * 速度
         */
        private int speed;

        /**
         * 总合
         */
        private int total;

        /**
         * 平均值
         */
        private float average;

        public BaseStat() {
        }

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

        private static BaseStat ofDomain(@Nullable PokemonDetailBaseStat domain) {
            if (domain == null) {
                return null;
            }
            return new BaseStat(domain.getHp(), domain.getAttack(), domain.getDefense(), domain.getSpAttack(),
                domain.getSpDefense(), domain.getSpeed(), domain.getTotal(), domain.getAverage());
        }
    }

    /**
     * 详细信息
     */
    @Getter
    @Setter
    public static class Detail {
        /**
         * 预览图片
         */
        private String imgUrl;

        /**
         * 分类
         */
        private String category;

        /**
         * 身高
         */
        private String height;

        /**
         * 体重
         */
        private String weight;

        /**
         * 体型
         */
        private String bodyStyle;

        /**
         * 捕获率
         */
        private String catchRate;

        /**
         * 性别比例，以逗号分隔
         */
        private String genderRatio;

        /**
         * 蛋组1
         */
        private String eggGroup1;

        /**
         * 蛋组2
         */
        private String eggGroup2;

        /**
         * 孵化时间
         */
        private String hatchTime;

        /**
         * 基础点数，以逗号分隔
         */
        private String effortValue;

        public Detail() {
        }

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

        private static Detail ofDomain(@Nullable PokemonDetail domain) {
            if (domain == null) {
                return null;
            }
            return new Detail(domain.getImgUrl(), domain.getCategory(), domain.getHeight(), domain.getWeight(),
                domain.getBodyStyle(), domain.getCatchRate(), domain.getGenderRatio(),
                domain.getEggGroup1(), domain.getEggGroup2(), domain.getHatchTime(), domain.getEffortValue());
        }
    }

    @Getter
    @Setter
    private static class LearnSetByLevelingUp {
        /**
         * 等级
         */
        private String level;

        /**
         * 等级（太阳/月亮）
         */
        @Deprecated
        private String level1;

        /**
         * 等级（究极之日/究极之月）
         */
        @Deprecated
        private String level2;

        /**
         * 招式名称
         */
        private String move;

        /**
         * 属性
         */
        private String type;

        /**
         * 分类
         */
        private String category;

        /**
         * 威力
         */
        private String power;

        /**
         * 命中
         */
        private String accuracy;

        /**
         * PP
         */
        private String pp;

        public LearnSetByLevelingUp() {
        }

        private LearnSetByLevelingUp(String level, String move, String type, String category,
                                     String power, String accuracy, String pp) {
            this.level = level;
            this.level1 = level;
            this.level2 = level;
            this.move = move;
            this.type = type;
            this.category = category;
            this.power = power;
            this.accuracy = accuracy;
            this.pp = pp;
        }

        private static LearnSetByLevelingUp ofDomain(@Nullable PokemonDetailLearnSetByLevelingUp domain) {
            if (domain == null) {
                return null;
            }
            return new LearnSetByLevelingUp(domain.getLevel(), domain.getMove(), domain.getType(),
                domain.getCategory(), domain.getPower(), domain.getAccuracy(), domain.getPp());
        }
    }

    @Getter
    @Setter
    private static class LearnSetByTechnicalMachine {
        /**
         * 招式学习器图片链接
         */
        private String imgUrl;

        /**
         * 招式学习器名称
         */
        private String technicalMachine;

        /**
         * 招式名称
         */
        private String move;

        /**
         * 属性
         */
        private String type;

        /**
         * 分类
         */
        private String category;

        /**
         * 威力
         */
        private String power;

        /**
         * 命中
         */
        private String accuracy;

        /**
         * PP
         */
        private String pp;

        public LearnSetByTechnicalMachine() {
        }

        private LearnSetByTechnicalMachine(String imgUrl, String technicalMachine, String move, String type,
                                           String category, String power, String accuracy, String pp) {
            this.imgUrl = imgUrl;
            this.technicalMachine = technicalMachine;
            this.move = move;
            this.type = type;
            this.category = category;
            this.power = power;
            this.accuracy = accuracy;
            this.pp = pp;
        }

        private static LearnSetByTechnicalMachine ofDomain(@Nullable PokemonDetailLearnSetByTechnicalMachine domain) {
            if (domain == null) {
                return null;
            }
            return new LearnSetByTechnicalMachine(domain.getImgUrl(), domain.getTechnicalMachine(), domain.getMove(),
                domain.getType(), domain.getCategory(), domain.getPower(), domain.getAccuracy(), domain.getPp());
        }
    }

    @Getter
    @Setter
    private static class LearnSetByBreeding {
        /**
         * 亲代
         */
        private String parent;

        /**
         * 招式名称
         */
        private String move;

        /**
         * 属性
         */
        private String type;

        /**
         * 分类
         */
        private String category;

        /**
         * 威力
         */
        private String power;

        /**
         * 命中
         */
        private String accuracy;

        /**
         * PP
         */
        private String pp;

        public LearnSetByBreeding() {
        }

        private LearnSetByBreeding(String parent, String move, String type, String category, String power,
                                   String accuracy, String pp) {
            this.parent = parent;
            this.move = move;
            this.type = type;
            this.category = category;
            this.power = power;
            this.accuracy = accuracy;
            this.pp = pp;
        }

        private static LearnSetByBreeding ofDomain(@Nullable PokemonDetailLearnSetByBreeding domain) {
            if (domain == null) {
                return null;
            }
            return new LearnSetByBreeding(domain.getParent(), domain.getMove(), domain.getType(), domain.getCategory(),
                domain.getPower(), domain.getAccuracy(), domain.getPp());
        }
    }

}
