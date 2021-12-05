package cn.fantasticmao.pokemon.web.bean;

import cn.fantasticmao.pokemon.web.domain.Move;
import cn.fantasticmao.pokemon.web.domain.MoveDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * MoveBean
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
@Getter
@ToString
public class MoveBean implements Comparable<MoveBean> {
    /**
     * 招式编号
     */
    private final int id;

    /**
     * 中文名称
     */
    private final String nameZh;

    /**
     * 日文名称
     */
    private final String nameJa;

    /**
     * 英文名称
     */
    private final String nameEn;

    /**
     * 属性
     */
    private final String type;

    /**
     * 分类
     */
    private final String category;

    /**
     * 威力
     */
    private final String power;

    /**
     * 命中
     */
    private final String accuracy;

    /**
     * PP
     */
    private final String pp;

    /**
     * 第几世代
     */
    private final int generation;

    /**
     * 招式详情
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Detail detail;

    @Override
    public int compareTo(@Nonnull MoveBean that) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), that.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getId(), that.getId())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    private MoveBean(int id, String nameZh, String nameJa, String nameEn, String type, String category, String power,
                     String accuracy, String pp, int generation, Detail detail) {
        this.id = id;
        this.nameZh = nameZh;
        this.nameJa = nameJa;
        this.nameEn = nameEn;
        this.type = type;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.generation = generation;
        this.detail = detail;
    }

    public static MoveBean ofDomain(@Nonnull Move move, @Nullable MoveDetail moveDetail) {
        return new MoveBean(move.getId(), move.getNameZh(), move.getNameJa(), move.getNameEn(), move.getType(),
            move.getCategory(), move.getPower(), move.getAccuracy(), move.getPp(), move.getGeneration(),
            Detail.ofDomain(moveDetail));
    }

    @Getter
    @ToString
    private static class Detail {
        /**
         * 招式描述
         */
        private final String desc;

        /**
         * 图片链接
         */
        private final String imgUrl;

        /**
         * 注意事项
         */
        private final String notes;

        /**
         * 作用范围
         */
        private final String scope;

        /**
         * 附加效果
         */
        private final String effect;

        private Detail(String desc, String imgUrl, String notes, String scope, String effect) {
            this.desc = desc;
            this.imgUrl = imgUrl;
            this.notes = notes;
            this.scope = scope;
            this.effect = effect;
        }

        private static Detail ofDomain(@Nullable MoveDetail domain) {
            if (domain == null) {
                return null;
            }
            return new Detail(domain.getDesc(), domain.getImgUrl(), domain.getNotes(), domain.getScope(),
                domain.getEffect());
        }
    }
}
