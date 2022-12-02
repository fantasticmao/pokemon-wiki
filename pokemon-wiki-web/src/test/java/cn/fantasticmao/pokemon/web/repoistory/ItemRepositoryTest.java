package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * ItemRepositoryTest
 *
 * @author fantasticmao
 * @since 2019-03-23
 */
public class ItemRepositoryTest extends SpringTest {
    @Resource
    private ItemRepository itemRepository;

    @Test
    public void findAll() {
        // FIXME
        List<Item> itemList = itemRepository.findAll();
        Assertions.assertNotNull(itemList);
        Assertions.assertEquals(1342, itemList.size());
    }

    @Test
    public void findByNameZh() {
        List<Item> itemList = itemRepository.findByNameZh("除虫喷雾");
        Assertions.assertNotNull(itemList);
        Assertions.assertEquals(1, itemList.size());

        Item repel = itemList.get(0);
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
        List<Item> itemList = itemRepository.find(0, 20);
        Assertions.assertNotNull(itemList);
        Assertions.assertEquals(20, itemList.size());
    }

}
