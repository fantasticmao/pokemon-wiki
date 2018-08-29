package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Move;
import cn.fantasticmao.pokemon.wiki.domain.MoveDetail;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * MoveBean
 *
 * @author maodh
 * @since 2018/8/29
 */
@Getter
@ToString
public class MoveBean implements Serializable, Comparable<MoveBean> {
    private final int id;
    private final String nameZh;
    private final String nameJa;
    private final String nameEn;
    private final String type;
    private final String category;
    private final String power;
    private final String accuracy;
    private final String pp;
    private final int generation;
    private final Detail detail;

    @Override
    public int compareTo(@Nonnull MoveBean moveBean) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), moveBean.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getId(), moveBean.getId())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    private MoveBean(int id, String nameZh, String nameJa, String nameEn, String type, String category, String power, String accuracy, String pp, int generation, Detail detail) {
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

    public static MoveBean ofDomain(Move move, MoveDetail moveDetail) {
        return new MoveBean(move.getId(), move.getNameZh(), move.getNameJa(), move.getNameEn(), move.getType(), move.getCategory(),
                move.getPower(), move.getAccuracy(), move.getPp(), move.getGeneration(), Detail.ofDomain(moveDetail));
    }

    @Getter
    @ToString
    private static class Detail implements Serializable {
        private final String desc;
        private final String imgUrl;
        private final String notes;
        private final String scope;
        private final String effect;

        private Detail(String desc, String imgUrl, String notes, String scope, String effect) {
            this.desc = desc;
            this.imgUrl = imgUrl;
            this.notes = notes;
            this.scope = scope;
            this.effect = effect;
        }

        private static Detail ofDomain(MoveDetail moveDetail) {
            return new Detail(moveDetail.getDesc(), moveDetail.getImgUrl(), moveDetail.getNotes(), moveDetail.getScope(), moveDetail.getEffect());
        }
    }
}
