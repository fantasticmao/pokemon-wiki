package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Move;
import cn.fantasticmao.pokemon.wiki.domain.MoveDetail;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "招式编号", example = "7")
    private final int id;
    @ApiModelProperty(value = "中文名称", example = "火焰拳")
    private final String nameZh;
    @ApiModelProperty(value = "日文名称", example = "ほのおのパンチ")
    private final String nameJa;
    @ApiModelProperty(value = "英文名称", example = "Fire Punch")
    private final String nameEn;
    @ApiModelProperty(value = "属性", example = "火")
    private final String type;
    @ApiModelProperty(value = "分类", example = "物理")
    private final String category;
    @ApiModelProperty(value = "威力", example = "75")
    private final String power;
    @ApiModelProperty(value = "命中", example = "100")
    private final String accuracy;
    @ApiModelProperty(value = "PP", example = "15")
    private final String pp;
    @ApiModelProperty(value = "第几世代", example = "1")
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
        @ApiModelProperty(value = "招式描述", example = "用充满火焰的拳头攻击对手。有时会让对手陷入灼伤状态。")
        private final String desc;
        @ApiModelProperty(value = "图片链接", example = "https://s1.52poke.wiki/assets/animoves/AniMove007.gif")
        private final String imgUrl;
        @ApiModelProperty(value = "注意事项", example = "是接触类招式 受守住影响 不受魔法反射影响 不可以被抢夺 受鹦鹉学舌影响 不受王者之证等类似道具影响")
        private final String notes;
        @ApiModelProperty(value = "作用范围", example = "除自身以外场上一只可以攻击到的宝可梦")
        private final String scope;
        @ApiModelProperty(value = "附加效果", example = "攻击目标造成伤害。 火焰拳有10%的几率使目标陷入灼伤状态。")
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
