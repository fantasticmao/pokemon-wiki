package cn.fantasticmao.pokemon.web.application.model;

import lombok.Data;

/**
 * ItemResponse
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Data
public class ItemResponse {
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

}
