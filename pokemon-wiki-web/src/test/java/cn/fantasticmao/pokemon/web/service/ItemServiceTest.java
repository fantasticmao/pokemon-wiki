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
        super.assertRepel(repel);
    }

    @Test
    public void listAll() {
        // FIXME page < 0
        List<ItemBean> itemBeanList = itemService.list(null, 50);
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
