package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

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
    public void test() {
        Item item = itemRepository.findById(1).orElseThrow(RuntimeException::new);
        Assert.assertNotNull(item);
    }

}