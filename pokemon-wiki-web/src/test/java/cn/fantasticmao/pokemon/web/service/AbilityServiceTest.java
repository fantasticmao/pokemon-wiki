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
        List<AbilityBean> abilityBeanList = abilityService.listByNameZh("茂盛");
        Assertions.assertNotNull(abilityBeanList);
        Assertions.assertEquals(1, abilityBeanList.size());
    }
}
