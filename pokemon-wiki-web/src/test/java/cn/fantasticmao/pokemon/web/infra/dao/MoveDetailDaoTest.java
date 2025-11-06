package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.MoveDetailPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * MoveDetailDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class MoveDetailDaoTest extends SpringTest {
    @Resource
    private MoveDetailDao moveDetailDao;

    @Test
    public void findByIdIn() {
        List<MoveDetailPo> moveDetailPoList = moveDetailDao.findByIdIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(moveDetailPoList);
        Assertions.assertEquals(2, moveDetailPoList.size());

        MoveDetailPo pound = moveDetailPoList.getFirst();
        Assertions.assertEquals(1, pound.getId());
        Assertions.assertEquals("拍击", pound.getNameZh());
        Assertions.assertEquals("使用长长的尾巴或手等拍打对手进行攻击。", pound.getDesc());
        Assertions.assertEquals("https://s1.52poke.wiki/assets/animoves/AniMove001.gif", pound.getImgUrl());
        Assertions.assertEquals("是接触类招式 受守住影响 不受魔法反射影响 不可以被抢夺 受鹦鹉学舌影响 受王者之证等类似道具影响", pound.getNotes());
        Assertions.assertEquals("除自身以外场上一只可以攻击到的宝可梦", pound.getScope());
        Assertions.assertEquals("攻击目标造成伤害。", pound.getEffect());
    }

}
