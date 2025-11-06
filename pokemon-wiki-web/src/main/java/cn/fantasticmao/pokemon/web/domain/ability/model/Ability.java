package cn.fantasticmao.pokemon.web.domain.ability.model;

import lombok.Data;

import javax.annotation.Nullable;

/**
 * 特性实体
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class Ability implements Comparable<Ability> {
    /**
     * 特性编号
     */
    private Integer id;

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
     * 特性描述
     */
    private String effect;

    /**
     * 第几世代
     */
    private Integer generation;

    /**
     * 特性详情
     */
    @Nullable
    private AbilityDetail detail;

    @Override
    public int compareTo(Ability that) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), that.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getId(), that.getId())) != 0) {
            return r;
        } else {
            return 0;
        }
    }
}
