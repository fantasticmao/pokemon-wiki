package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * ItemRepositoryTest
 *
 * @author maomao
 * @since 2019-03-23
 */
public class ItemRepositoryTest extends SpringTest {
    @Resource
    private ItemRepository itemRepository;

    @Test
    public void findAll() {
        List<Item> itemList = itemRepository.findAll();
        Assertions.assertNotNull(itemList);
        System.out.println(itemList);
    }

}