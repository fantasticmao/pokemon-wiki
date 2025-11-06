package cn.fantasticmao.pokemon.web.infra.repository;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.MovePo;
import cn.fantasticmao.pokemon.web.infra.dao.MoveDao;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * MoveDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class MoveDaoTest extends SpringTest {
    @Resource
    private MoveDao moveDao;

    @Test
    public void findByNameZh() {
        List<MovePo> movePoList = moveDao.findByName("拍击", null);
        Assertions.assertNotNull(movePoList);
        Assertions.assertEquals(1, movePoList.size());

        MovePo pound = movePoList.getFirst();
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
    public void findByNameEn() {
        List<MovePo> movePoList = moveDao.findByName(null, "Pound");
        Assertions.assertNotNull(movePoList);
        Assertions.assertEquals(1, movePoList.size());

        MovePo pound = movePoList.getFirst();
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
        List<MovePo> movePoList = moveDao.find(20, 0);
        Assertions.assertNotNull(movePoList);
        Assertions.assertEquals(20, movePoList.size());
    }
}
