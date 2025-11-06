package cn.fantasticmao.pokemon.web.domain.ability.model;

import lombok.Data;

/**
 * 特性详情值对象
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class AbilityDetail {
    /**
     * 特性描述，以空格分隔
     */
    private String desc;

    /**
     * 特性效果，以空格分隔
     */
    private String effect;

    /**
     * 拥有此特性的宝可梦，以逗号分隔
     */
    private String pokemons;
}
