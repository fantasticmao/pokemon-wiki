package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.Ability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * AbilityRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class AbilityRepositoryTest extends SpringTest {
    @Resource
    private AbilityRepository abilityRepository;

    @Test
    public void findByNameZh() {
        List<Ability> abilityList = abilityRepository.findByNameZh("恶臭");
        Assertions.assertNotNull(abilityList);
        Assertions.assertEquals(1, abilityList.size());

        Ability stench = abilityList.get(0);
        Assertions.assertEquals("恶臭", stench.getNameZh());
        Assertions.assertEquals("あくしゅう", stench.getNameJa());
        Assertions.assertEquals("Stench", stench.getNameEn());
        Assertions.assertEquals("通过释放臭臭的气味，在攻击的时候，有时会使对手畏缩。", stench.getEffect());
        Assertions.assertEquals(3, stench.getGeneration());
    }

    @Test
    public void find() {
        List<Ability> abilityList = abilityRepository.find(0, 20);
        Assertions.assertNotNull(abilityList);
        Assertions.assertEquals(20, abilityList.size());
    }

}
