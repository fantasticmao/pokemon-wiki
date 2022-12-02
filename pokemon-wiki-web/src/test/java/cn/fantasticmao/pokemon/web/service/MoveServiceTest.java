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
        List<MoveBean> moveBeanList = moveService.listByNameZh("拍击");
        Assertions.assertNotNull(moveBeanList);
        Assertions.assertEquals(1, moveBeanList.size());

        MoveBean pound = moveBeanList.get(0);
        Assertions.assertEquals(1, pound.getId());
        Assertions.assertEquals("拍击", pound.getNameZh());
        Assertions.assertEquals("はたく", pound.getNameJa());
        Assertions.assertEquals("Pound", pound.getNameEn());
        Assertions.assertEquals("一般", pound.getType());
        Assertions.assertEquals("物理", pound.getCategory());
        Assertions.assertEquals("40", pound.getPower());
        Assertions.assertEquals("100", pound.getAccuracy());
        Assertions.assertEquals("35", pound.getPp());
        Assertions.assertEquals(1, pound.getGeneration());
        Assertions.assertEquals("使用长长的尾巴或手等拍打对手进行攻击。", pound.getDetail().getDesc());
        Assertions.assertEquals("https://s1.52poke.wiki/assets/animoves/AniMove001.gif", pound.getDetail().getImgUrl());
        Assertions.assertEquals("是接触类招式 受守住影响 不受魔法反射影响 不可以被抢夺 受鹦鹉学舌影响 受王者之证等类似道具影响", pound.getDetail().getNotes());
        Assertions.assertEquals("除自身以外场上一只可以攻击到的宝可梦", pound.getDetail().getScope());
        Assertions.assertEquals("攻击目标造成伤害。", pound.getDetail().getEffect());
    }

    @Test
    public void list() {
        List<MoveBean> moveBeanList = moveService.list(0, 20);
        Assertions.assertNotNull(moveBeanList);
        Assertions.assertEquals(20, moveBeanList.size());
    }

}
