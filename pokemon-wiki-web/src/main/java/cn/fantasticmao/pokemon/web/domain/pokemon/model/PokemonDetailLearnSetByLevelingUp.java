package cn.fantasticmao.pokemon.web.domain.pokemon.model;

import lombok.Data;

/**
 * 宝可梦可学会的招式值对象
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class PokemonDetailLearnSetByLevelingUp {
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
