package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.MoveDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * MoveDetailRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class MoveDetailRepositoryTest extends SpringTest {
    @Resource
    private MoveDetailRepository moveDetailRepository;

    @Test
    public void findByIdIn() {
        List<MoveDetail> moveDetailList = moveDetailRepository.findByIdIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(moveDetailList);
        Assertions.assertEquals(2, moveDetailList.size());
    }

}