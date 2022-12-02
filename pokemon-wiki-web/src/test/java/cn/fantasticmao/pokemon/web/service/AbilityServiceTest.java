package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.bean.AbilityBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * AbilityServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class AbilityServiceTest extends SpringTest {
    @Resource
    private AbilityService abilityService;

    @Test
    public void listByNameZh() {
        List<AbilityBean> abilityBeanList = abilityService.listByNameZh("恶臭");
        Assertions.assertNotNull(abilityBeanList);
        Assertions.assertEquals(1, abilityBeanList.size());

        AbilityBean stench = abilityBeanList.get(0);
        Assertions.assertEquals(1, stench.getId());
        Assertions.assertEquals("恶臭", stench.getNameZh());
        Assertions.assertEquals("あくしゅう", stench.getNameJa());
        Assertions.assertEquals("Stench", stench.getNameEn());
        Assertions.assertEquals(3, stench.getGeneration());
        Assertions.assertEquals("通过释放臭臭的气味，在攻击的时候，有时会使对手畏缩。", stench.getDetail().getDesc());
        Assertions.assertEquals("对战中 使用招式攻击对手造成伤害时，对方有10%几率陷入畏缩状态。 对战外 当该特性的宝可梦配置在同行宝可梦首位时，野生宝可梦出现机率降低50%。", stench.getDetail().getEffect());
        Assertions.assertEquals("臭臭花,臭泥,臭臭泥,瓦斯弹,双弹瓦斯,臭鼬噗,坦克臭鼬,破破袋,灰尘山", stench.getDetail().getPokemons());
    }

    @Test
    public void list() {
        List<AbilityBean> abilityBeanList = abilityService.list(0, 20);
        Assertions.assertNotNull(abilityBeanList);
        Assertions.assertEquals(20, abilityBeanList.size());
    }
}
