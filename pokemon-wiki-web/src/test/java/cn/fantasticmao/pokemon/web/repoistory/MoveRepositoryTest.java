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
        List<Move> moveList = moveRepository.findByNameZh("拍击");
        Assertions.assertNotNull(moveList);
        Assertions.assertEquals(1, moveList.size());

        Move pound = moveList.get(0);
        Assertions.assertEquals(1, pound.getId());
        Assertions.assertEquals("拍击", pound.getNameZh());
        Assertions.assertEquals("はたく", pound.getNameJa());
        Assertions.assertEquals("Pound", pound.getNameEn());
        Assertions.assertEquals("一般", pound.getType());
        Assertions.assertEquals("物理", pound.getCategory());
        Assertions.assertEquals("40", pound.getPower());
        Assertions.assertEquals("100", pound.getAccuracy());
        Assertions.assertEquals("35", pound.getPp());
        Assertions.assertEquals(1, pound.getGeneration());
    }

    @Test
    public void find() {
        List<Move> moveList = moveRepository.find(0, 20);
        Assertions.assertNotNull(moveList);
        Assertions.assertEquals(20, moveList.size());
    }
}
