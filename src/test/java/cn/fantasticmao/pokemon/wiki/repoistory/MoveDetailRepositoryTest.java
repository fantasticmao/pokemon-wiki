package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.MoveDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
    public void findByIdIn() {
        List<MoveDetail> moveDetailList = moveDetailRepository.findByIdIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(moveDetailList);
        System.out.println(moveDetailList);
    }

}