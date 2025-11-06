package cn.fantasticmao.pokemon.web.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * PokemonResponse
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Data
public class PokemonResponse {
    /**
     * 全国图鉴
     */
    private Integer index;

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
    private Integer generation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Detail detail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BaseStat baseStat;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LearnSetByLevelingUp> learnSetByLevelingUp;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LearnSetByTechnicalMachine> learnSetByTechnicalMachine;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<LearnSetByBreeding> learnSetByBreeding;

    /**
     * 详细信息
     */
    @Data
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
    }

    /**
     * 种族值
     */
    @Data
    public static class BaseStat {
        /**
         * HP
         */
        private Integer hp;

        /**
         * 攻击
         */
        private Integer attack;

        /**
         * 防御
         */
        private Integer defense;

        /**
         * 特攻
         */
        private Integer spAttack;

        /**
         * 特防
         */
        private Integer spDefense;

        /**
         * 速度
         */
        private Integer speed;

        /**
         * 总合
         */
        private Integer total;

        /**
         * 平均值
         */
        private Float average;
    }

    @Data
    public static class LearnSetByLevelingUp {
        /**
         * 等级
         */
        private String level;

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
    }

    @Data
    public static class LearnSetByTechnicalMachine {
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
    }

    @Data
    public static class LearnSetByBreeding {
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
    }

}
