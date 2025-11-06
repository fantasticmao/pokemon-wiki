package cn.fantasticmao.pokemon.web.domain.pokemon.model;

import lombok.Data;

/**
 * 宝可梦能使用的招式学习器值对象
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class PokemonDetailLearnSetByTechnicalMachine {
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
