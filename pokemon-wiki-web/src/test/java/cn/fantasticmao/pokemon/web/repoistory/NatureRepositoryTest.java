package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.Nature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * NatureRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class NatureRepositoryTest extends SpringTest {
    @Resource
    private NatureRepository natureRepository;

    @Test
    public void findAll() {
        List<Nature> natureList = natureRepository.findAll();
        Assertions.assertNotNull(natureList);
        Assertions.assertEquals(25, natureList.size());
    }
}