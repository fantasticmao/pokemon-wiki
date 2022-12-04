package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.AbilityDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * AbilityDetailRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class AbilityDetailRepositoryTest extends SpringTest {
    @Resource
    private AbilityDetailRepository abilityDetailRepository;

    @Test
    public void findByIdIn() {
        List<AbilityDetail> abilityDetailList = abilityDetailRepository.findByIdIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(abilityDetailList);
        Assertions.assertEquals(2, abilityDetailList.size());
    }

}
