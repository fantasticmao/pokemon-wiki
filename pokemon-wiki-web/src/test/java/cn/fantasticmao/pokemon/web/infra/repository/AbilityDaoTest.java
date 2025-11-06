package cn.fantasticmao.pokemon.web.infra.repository;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.dao.AbilityDao;
import cn.fantasticmao.pokemon.web.infra.model.AbilityPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * AbilityDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class AbilityDaoTest extends SpringTest {
    @Resource
    private AbilityDao abilityDao;

    @Test
    public void findByNameZh() {
        List<AbilityPo> abilityPoList = abilityDao.findByName("恶臭", null);
        Assertions.assertNotNull(abilityPoList);
        Assertions.assertEquals(1, abilityPoList.size());

        AbilityPo stench = abilityPoList.getFirst();
        Assertions.assertEquals(1, stench.getId());
        Assertions.assertEquals("恶臭", stench.getNameZh());
        Assertions.assertEquals("あくしゅう", stench.getNameJa());
        Assertions.assertEquals("Stench", stench.getNameEn());
        Assertions.assertEquals("通过释放臭臭的气味，在攻击的时候，有时会使对手畏缩。", stench.getEffect());
        Assertions.assertEquals(3, stench.getGeneration());
    }

    @Test
    public void findByNameEn() {
        List<AbilityPo> abilityPoList = abilityDao.findByName(null, "Stench");
        Assertions.assertNotNull(abilityPoList);
        Assertions.assertEquals(1, abilityPoList.size());

        AbilityPo stench = abilityPoList.getFirst();
        Assertions.assertEquals(1, stench.getId());
        Assertions.assertEquals("恶臭", stench.getNameZh());
        Assertions.assertEquals("あくしゅう", stench.getNameJa());
        Assertions.assertEquals("Stench", stench.getNameEn());
        Assertions.assertEquals("通过释放臭臭的气味，在攻击的时候，有时会使对手畏缩。", stench.getEffect());
        Assertions.assertEquals(3, stench.getGeneration());
    }

    @Test
    public void find() {
        List<AbilityPo> abilityPoList = abilityDao.find(20, 0);
        Assertions.assertNotNull(abilityPoList);
        Assertions.assertEquals(20, abilityPoList.size());
    }

}
