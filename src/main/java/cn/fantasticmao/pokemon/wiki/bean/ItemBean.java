package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Item;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * ItemBean
 *
 * @author maomao
 * @since 2019-03-23
 */
@Getter
@ToString
public class ItemBean implements Serializable, Comparable<ItemBean> {
    @ApiModelProperty(value = "道具编号", example = "1")
    private final int id;
    @ApiModelProperty(value = "道具类型", example = "野外使用和其它类别道具")
    private final String type;
    @ApiModelProperty(value = "预览图片", example = "https://s1.52poke.wiki/wiki/7/7d/Bag_%E9%99%A4%E8%99%AB%E5%96%B7%E9%9B%BE_Sprite.png")
    private final String imgUrl;
    @ApiModelProperty(value = "中文名称", example = "除虫喷雾")
    private final String nameZh;
    @ApiModelProperty(value = "日文名称", example = "むしよけスプレー")
    private final String nameJa;
    @ApiModelProperty(value = "英文名称", example = "Repel")
    private final String nameEn;
    @ApiModelProperty(value = "道具描述", example = "使用后，在较短的一段时间内，弱小的野生宝可梦将完全不会出现。")
    private final String desc;
    @ApiModelProperty(value = "第几世代", example = "0")
    private final int generation;

    @Override
    public int compareTo(@Nonnull ItemBean itemBean) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), itemBean.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getId(), itemBean.getId())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    private ItemBean(int id, String type, String imgUrl, String nameZh, String nameJa, String nameEn, String desc, int generation) {
        this.id = id;
        this.type = type;
        this.imgUrl = imgUrl;
        this.nameZh = nameZh;
        this.nameJa = nameJa;
        this.nameEn = nameEn;
        this.desc = desc;
        this.generation = generation;
    }

    public static ItemBean ofDomain(Item item) {
        return new ItemBean(item.getId(), item.getType(), item.getImgUrl(), item.getNameZh(), item.getNameJa(),
            item.getNameEn(), item.getDesc(), item.getGeneration());
    }
}
