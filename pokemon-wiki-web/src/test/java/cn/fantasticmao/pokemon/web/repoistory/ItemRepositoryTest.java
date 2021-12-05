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
    }

    @Test
    public void find() {
        List<Item> itemList = itemRepository.find(0, 20);
        Assertions.assertNotNull(itemList);
        Assertions.assertEquals(20, itemList.size());
    }

}