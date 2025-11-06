package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.item.model.Item;
import cn.fantasticmao.pokemon.web.domain.item.service.ItemDomainService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ItemDomainServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class ItemDomainServiceTest extends SpringTest {
    @Resource
    private ItemDomainService itemDomainService;

    @Test
    public void listByNameZh() {
        List<Item> itemList = itemDomainService.listByName("除虫喷雾", null);
        Assertions.assertNotNull(itemList);

        Item repel = itemList.getFirst();
        super.assertRepel(repel);
    }

    @Test
    public void listByNameEn() {
        List<Item> itemList = itemDomainService.listByName(null, "Repel");
        Assertions.assertNotNull(itemList);

        Item repel = itemList.getFirst();
        super.assertRepel(repel);
    }

    @Test
    public void list() {
        List<Item> itemList = itemDomainService.list(0, 20);
        Assertions.assertNotNull(itemList);
        Assertions.assertEquals(20, itemList.size());
    }
}
