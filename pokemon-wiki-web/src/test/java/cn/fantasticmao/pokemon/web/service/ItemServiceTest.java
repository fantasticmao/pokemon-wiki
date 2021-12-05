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
    public void listAll() {
        // FIXME page < 0
        List<ItemBean> itemBeanList = itemService.list(-1, 0);
        Assertions.assertNotNull(itemBeanList);
    }

    @Test
    public void list() {
        List<ItemBean> itemBeanList = itemService.list(0, 20);
        Assertions.assertNotNull(itemBeanList);
        Assertions.assertEquals(20, itemBeanList.size());
    }
}
