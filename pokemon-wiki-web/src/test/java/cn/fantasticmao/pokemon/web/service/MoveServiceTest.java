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
        List<MoveBean> moveBeanList = moveService.listByNameZh("飞叶快刀");
        Assertions.assertNotNull(moveBeanList);
        Assertions.assertEquals(1, moveBeanList.size());
    }

}
