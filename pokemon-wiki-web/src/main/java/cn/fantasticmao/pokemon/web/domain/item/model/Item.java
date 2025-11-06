package cn.fantasticmao.pokemon.web.domain.item.model;

import lombok.Data;

/**
 * 道具实体
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class Item implements Comparable<Item> {
    /**
     * 道具编号
     */
    private Integer id;

    /**
     * 道具类型
     */
    private String type;

    /**
     * 预览图片
     */
    private String imgUrl;

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
     * 道具描述
     */
    private String desc;

    /**
     * 第几世代
     */
    private Integer generation;

    @Override
    public int compareTo(Item that) {
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
