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
    private final int id;
    private final String type;
    private final String imgUrl;
    private final String nameZh;
    private final String nameJa;
    private final String nameEn;
    private final String desc;
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
