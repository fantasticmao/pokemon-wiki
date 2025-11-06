package cn.fantasticmao.pokemon.web.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * AbilityResponse
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Data
public class AbilityResponse {
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
     * 第几世代
     */
    private Integer generation;

    /**
     * 特性详情
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Detail detail;

    @Data
    public static class Detail {
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
}
