package cn.fantasticmao.pokemon.web.bean;

import cn.fantasticmao.pokemon.web.domain.Item;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;

/**
 * ItemBean
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
@Getter
@ToString
public class ItemBean implements Comparable<ItemBean> {
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
    public int compareTo(@Nonnull ItemBean that) {
        int r;
        if ((r = Integer.compare(this.getGeneration(), that.getGeneration())) != 0) {
            return r;
        } else if ((r = Integer.compare(this.getId(), that.getId())) != 0) {
            return r;
        } else {
            return 0;
        }
    }

    private ItemBean(int id, String type, String imgUrl, String nameZh, String nameJa, String nameEn, String desc,
                     int generation) {
        this.id = id;
        this.type = type;
        this.imgUrl = imgUrl;
        this.nameZh = nameZh;
        this.nameJa = nameJa;
        this.nameEn = nameEn;
        this.desc = desc;
        this.generation = generation;
    }

    public static ItemBean ofDomain(@Nonnull Item item) {
        return new ItemBean(item.getId(), item.getType(), item.getImgUrl(), item.getNameZh(), item.getNameJa(),
            item.getNameEn(), item.getDesc(), item.getGeneration());
    }
}
