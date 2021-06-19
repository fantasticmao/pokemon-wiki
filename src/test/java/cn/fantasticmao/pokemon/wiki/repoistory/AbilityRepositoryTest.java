package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Ability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

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
    public void findByNameZh() {
        List<Ability> abilityList = abilityRepository.findByNameZh("茂盛");
        Assertions.assertNotNull(abilityList);
        System.out.println(abilityList);
    }

}