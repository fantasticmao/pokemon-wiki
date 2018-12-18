package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.MoveDetail;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * MoveDetailRepositoryTest
 *
 * @author maodh
 * @since 2018/8/29
 */
public class MoveDetailRepositoryTest extends SpringTest {
    @Resource
    private MoveDetailRepository moveDetailRepository;

    @Test
    public void findOne() {
        MoveDetail moveDetail = moveDetailRepository.findById(1).orElseThrow(RuntimeException::new);
        Assert.assertNotNull(moveDetail);
    }

}