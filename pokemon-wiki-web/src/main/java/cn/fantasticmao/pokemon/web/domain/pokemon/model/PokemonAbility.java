package cn.fantasticmao.pokemon.web.domain.pokemon.model;

import lombok.Data;

/**
 * 宝可梦能力值对象
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class PokemonAbility {
    /**
     * 中文名称
     */
    private String nameZh;

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
}
