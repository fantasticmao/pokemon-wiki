package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.ability.model.Ability;
import cn.fantasticmao.pokemon.web.domain.ability.service.AbilityDomainService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * AbilityDomainServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class AbilityDomainServiceTest extends SpringTest {
    @Resource
    private AbilityDomainService abilityDomainService;

    @Test
    public void listByNameZh() {
        List<Ability> abilityList = abilityDomainService.listByName("恶臭", null);
        Assertions.assertNotNull(abilityList);
        Assertions.assertEquals(1, abilityList.size());

        Ability stench = abilityList.getFirst();
        super.assertStench(stench);
    }

    @Test
    public void listByNameEn() {
        List<Ability> abilityList = abilityDomainService.listByName(null, "Stench");
        Assertions.assertNotNull(abilityList);
        Assertions.assertEquals(1, abilityList.size());

        Ability stench = abilityList.getFirst();
        super.assertStench(stench);
    }

    @Test
    public void list() {
        List<Ability> abilityList = abilityDomainService.list(0, 20);
        Assertions.assertNotNull(abilityList);
        Assertions.assertEquals(20, abilityList.size());
    }
}
