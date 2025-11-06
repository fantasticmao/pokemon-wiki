package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.ItemPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ItemDaoTest
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public class ItemDaoTest extends SpringTest {
    @Resource
    private ItemDao itemDao;

    @Test
    public void findByNameZh() {
        List<ItemPo> itemPoList = itemDao.findByName("除虫喷雾", null);
        Assertions.assertNotNull(itemPoList);
        Assertions.assertEquals(1, itemPoList.size());

        ItemPo repel = itemPoList.getFirst();
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
    public void findByNameEn() {
        List<ItemPo> itemPoList = itemDao.findByName(null, "Repel");
        Assertions.assertNotNull(itemPoList);
        Assertions.assertEquals(3, itemPoList.size());

        ItemPo repel = itemPoList.getFirst();
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
    public void find() {
        List<ItemPo> itemPoList = itemDao.find(20, 0);
        Assertions.assertNotNull(itemPoList);
        Assertions.assertEquals(20, itemPoList.size());
    }

}
