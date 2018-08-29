package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Nature;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * NatureRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class NatureRepositoryTest extends SpringTest {
    @Resource
    private NatureRepository natureRepository;

    @Test
    public void findOne() {
        Nature nature = natureRepository.findOne(1);
        Assert.assertNotNull(nature);
    }
}