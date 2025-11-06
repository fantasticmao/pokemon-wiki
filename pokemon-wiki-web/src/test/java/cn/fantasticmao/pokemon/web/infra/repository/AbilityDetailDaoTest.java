package cn.fantasticmao.pokemon.web.infra.repository;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.dao.AbilityDetailDao;
import cn.fantasticmao.pokemon.web.infra.model.AbilityDetailPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * AbilityDetailDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class AbilityDetailDaoTest extends SpringTest {
    @Resource
    private AbilityDetailDao abilityDetailDao;

    @Test
    public void findByIdIn() {
        List<AbilityDetailPo> abilityDetailPoList = abilityDetailDao.findByIdIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(abilityDetailPoList);
        Assertions.assertEquals(2, abilityDetailPoList.size());
    }

}
