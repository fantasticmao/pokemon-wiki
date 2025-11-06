package cn.fantasticmao.pokemon.web.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * MoveResponse
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Data
public class MoveResponse {
    /**
     * 招式编号
     */
    private int id;

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
    private int generation;

    /**
     * 招式详情
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Detail detail;

    @Data
    public static class Detail {
        /**
         * 招式描述
         */
        private String desc;

        /**
         * 图片链接
         */
        private String imgUrl;

        /**
         * 注意事项
         */
        private String notes;

        /**
         * 作用范围
         */
        private String scope;

        /**
         * 附加效果
         */
        private String effect;
    }
}
