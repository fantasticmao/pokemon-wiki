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
 * @author maomao
 * @since 2021-10-31
 */
public class ItemServiceTest extends SpringTest {
    @Resource
    private ItemService itemService;

    @Test
    public void listAll() {
        // FIXME
        List<ItemBean> itemBeanList = itemService.listAll();
        Assertions.assertNotNull(itemBeanList);
    }
}
