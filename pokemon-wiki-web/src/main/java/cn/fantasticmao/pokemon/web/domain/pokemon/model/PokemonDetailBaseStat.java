package cn.fantasticmao.pokemon.web.domain.pokemon.model;

import lombok.Data;

/**
 * 宝可梦种族值值对象
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class PokemonDetailBaseStat {
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
