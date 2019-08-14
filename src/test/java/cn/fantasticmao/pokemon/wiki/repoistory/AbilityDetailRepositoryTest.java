package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.AbilityDetail;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
    public void findByIdIn() {
        List<AbilityDetail> abilityDetailList = abilityDetailRepository.findByIdIn(Arrays.asList(1, 2));
        Assert.assertNotNull(abilityDetailList);
        System.out.println(abilityDetailList);
    }

}