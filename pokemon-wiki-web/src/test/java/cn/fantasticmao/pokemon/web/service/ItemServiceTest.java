package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.bean.ItemBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * ItemServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class ItemServiceTest extends SpringTest {
    @Resource
    private ItemService itemService;

    @Test
    public void listByNameZh() {
        List<ItemBean> itemBeanList = itemService.listByNameZh("除虫喷雾");
        Assertions.assertNotNull(itemBeanList);

        ItemBean repel = itemBeanList.get(0);
        Assertions.assertEquals(1, repel.getId());
        Assertions.assertEquals("道具 - 野外使用的道具", repel.getType());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/e/ef/Bag_%E9%99%A4%E8%99%AB%E5%96%B7%E9%9B%BE_SV_Sprite.png/30px-Bag_%E9%99%A4%E8%99%AB%E5%96%B7%E9%9B%BE_SV_Sprite.png", repel.getImgUrl());
        Assertions.assertEquals("除虫喷雾", repel.getNameZh());
        Assertions.assertEquals("むしよけスプレー", repel.getNameJa());
        Assertions.assertEquals("Repel", repel.getNameEn());
        Assertions.assertEquals("使用后，在较短的一段时间内，弱小的野生宝可梦将完全不会出现。", repel.getDesc());
        Assertions.assertEquals(0, repel.getGeneration());
    }

    @Test
    public void listAll() {
        // FIXME page < 0
        List<ItemBean> itemBeanList = itemService.list(-1, 50);
        Assertions.assertNotNull(itemBeanList);
        Assertions.assertEquals(1342, itemBeanList.size());
    }

    @Test
    public void list() {
        List<ItemBean> itemBeanList = itemService.list(0, 20);
        Assertions.assertNotNull(itemBeanList);
        Assertions.assertEquals(20, itemBeanList.size());
    }
}
