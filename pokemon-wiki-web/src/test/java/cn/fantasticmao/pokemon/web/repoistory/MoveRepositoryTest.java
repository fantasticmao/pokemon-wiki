package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * MoveRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class MoveRepositoryTest extends SpringTest {
    @Resource
    private MoveRepository moveRepository;

    @Test
    public void findByNameZh() {
        List<Move> moveList = moveRepository.findByNameZh("飞叶快刀");
        Assertions.assertNotNull(moveList);
        Assertions.assertEquals(1, moveList.size());
    }

    @Test
    public void find() {
        List<Move> moveList = moveRepository.find(0, 20);
        Assertions.assertNotNull(moveList);
        Assertions.assertEquals(20, moveList.size());
    }
}