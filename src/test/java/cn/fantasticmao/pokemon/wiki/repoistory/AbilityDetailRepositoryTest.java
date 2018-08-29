package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.AbilityDetail;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * AbilityDetailRepositoryTest
 *
 * @author maodh
 * @since 2018/8/29
 */
public class AbilityDetailRepositoryTest extends SpringTest {
    @Resource
    private AbilityDetailRepository abilityDetailRepository;

    @Test
    public void findOne() {
        AbilityDetail abilityDetail = abilityDetailRepository.findOne(1);
        Assert.assertNotNull(abilityDetail);
    }

}