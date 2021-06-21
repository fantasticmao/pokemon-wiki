package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

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
    public void findByNameZh() {
        List<Move> moveList = moveRepository.findByNameZh("飞叶快刀");
        Assertions.assertNotNull(moveList);
        System.out.println(moveList);
    }
}