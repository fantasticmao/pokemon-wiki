package cn.fantasticmao.pokemon.web.domain.pokemon.model;

import lombok.Data;

/**
 * 宝可梦详细信息值对象
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class PokemonDetail {
    /**
     * 预览图片
     */
    private String imgUrl;

    /**
     * 属性
     */
    private String type;

    /**
     * 分类
     */
    private String category;

    /**
     * 特性
     */
    private String ability;

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
