package cn.fantasticmao.pokemon.wiki.bean;

import cn.fantasticmao.pokemon.wiki.domain.Item;
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
    /**
     * 道具编号
     */
    private final int id;

    /**
     * 道具类型
     */
    private final String type;

    /**
     * 预览图片
     */
    private final String imgUrl;

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
     * 道具描述
     */
    private final String desc;

    /**
     * 第几世代
     */
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
