package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.bean.MoveBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * MoveServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class MoveServiceTest extends SpringTest {
    @Resource
    private MoveService moveService;

    @Test
    public void listByNameZh() {
        List<MoveBean> moveBeanList = moveService.listByName("拍击", null);
        Assertions.assertNotNull(moveBeanList);
        Assertions.assertEquals(1, moveBeanList.size());

        MoveBean pound = moveBeanList.get(0);
        super.assertPound(pound);
    }

    @Test
    public void listByNameEn() {
        List<MoveBean> moveBeanList = moveService.listByName(null, "Pound");
        Assertions.assertNotNull(moveBeanList);
        Assertions.assertEquals(1, moveBeanList.size());

        MoveBean pound = moveBeanList.get(0);
        super.assertPound(pound);
    }

    @Test
    public void list() {
        List<MoveBean> moveBeanList = moveService.list(0, 20);
        Assertions.assertNotNull(moveBeanList);
        Assertions.assertEquals(20, moveBeanList.size());
    }

}
