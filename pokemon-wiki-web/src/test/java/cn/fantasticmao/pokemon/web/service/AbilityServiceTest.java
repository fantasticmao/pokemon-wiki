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
        List<AbilityBean> abilityBeanList = abilityService.listByName("恶臭", null);
        Assertions.assertNotNull(abilityBeanList);
        Assertions.assertEquals(1, abilityBeanList.size());

        AbilityBean stench = abilityBeanList.get(0);
        super.assertStench(stench);
    }

    @Test
    public void listByNameEn() {
        List<AbilityBean> abilityBeanList = abilityService.listByName(null, "Stench");
        Assertions.assertNotNull(abilityBeanList);
        Assertions.assertEquals(1, abilityBeanList.size());

        AbilityBean stench = abilityBeanList.get(0);
        super.assertStench(stench);
    }

    @Test
    public void list() {
        List<AbilityBean> abilityBeanList = abilityService.list(0, 20);
        Assertions.assertNotNull(abilityBeanList);
        Assertions.assertEquals(20, abilityBeanList.size());
    }
}
