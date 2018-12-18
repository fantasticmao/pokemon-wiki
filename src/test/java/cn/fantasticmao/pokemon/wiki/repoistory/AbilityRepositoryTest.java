package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Ability;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * AbilityRepositoryTest
 *
 * @author maodh
 * @since 2018/8/29
 */
public class AbilityRepositoryTest extends SpringTest {
    @Resource
    private AbilityRepository abilityRepository;

    @Test
    public void findOne() {
        Ability ability = abilityRepository.findById(1).orElseThrow(RuntimeException::new);
        Assert.assertNotNull(ability);
    }

}