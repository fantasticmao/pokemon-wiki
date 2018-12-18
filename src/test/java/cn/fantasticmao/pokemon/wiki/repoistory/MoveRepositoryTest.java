package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Move;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * MoveRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class MoveRepositoryTest extends SpringTest {
    @Resource
    private MoveRepository moveRepository;

    @Test
    public void findOne() {
        Move move = moveRepository.findById(1).orElseThrow(RuntimeException::new);
        Assert.assertNotNull(move);
    }
}