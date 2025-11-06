package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.move.model.Move;
import cn.fantasticmao.pokemon.web.domain.move.service.MoveDomainService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * MoveDomainServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class MoveDomainServiceTest extends SpringTest {
    @Resource
    private MoveDomainService moveDomainService;

    @Test
    public void listByNameZh() {
        List<Move> moveList = moveDomainService.listByName("拍击", null);
        Assertions.assertNotNull(moveList);
        Assertions.assertEquals(1, moveList.size());

        Move pound = moveList.getFirst();
        super.assertPound(pound);
    }

    @Test
    public void listByNameEn() {
        List<Move> moveList = moveDomainService.listByName(null, "Pound");
        Assertions.assertNotNull(moveList);
        Assertions.assertEquals(1, moveList.size());

        Move pound = moveList.getFirst();
        super.assertPound(pound);
    }

    @Test
    public void list() {
        List<Move> moveList = moveDomainService.list(0, 20);
        Assertions.assertNotNull(moveList);
        Assertions.assertEquals(20, moveList.size());
    }

}
