package cn.fantasticmao.pokemon.web.domain.move.model;

import lombok.Data;

import javax.annotation.Nullable;

/**
 * 招式实体
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class Move implements Comparable<Move> {
    /**
     * 招式编号
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

    /**
     * 第几世代
     */
    private Integer generation;

    /**
     * 招式详情
     */
    @Nullable
    private MoveDetail detail;

    @Override
    public int compareTo(Move that) {
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
