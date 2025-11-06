package cn.fantasticmao.pokemon.web.domain.move.model;

import lombok.Data;

/**
 * 招式详情值对象
 *
 * @author fantasticmao
 * @since 2025-11-05
 */
@Data
public class MoveDetail {
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
