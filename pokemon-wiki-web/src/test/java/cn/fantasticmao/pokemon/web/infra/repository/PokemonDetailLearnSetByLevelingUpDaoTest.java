package cn.fantasticmao.pokemon.web.infra.repository;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDetailLearnSetByLevelingUpDao;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByLevelingUpPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/**
 * PokemonDetailLearnSetByLevelingUpDaoTest
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public class PokemonDetailLearnSetByLevelingUpDaoTest extends SpringTest {
    @Resource
    private PokemonDetailLearnSetByLevelingUpDao pokemonDetailLearnSetByLevelingUpDao;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailLearnSetByLevelingUpPo> pokemonDetailLearnSetByLevelingUpPoList =
            pokemonDetailLearnSetByLevelingUpDao.findByIndexIn(Collections.singletonList(1));
        Assertions.assertNotNull(pokemonDetailLearnSetByLevelingUpPoList);
        Assertions.assertTrue(pokemonDetailLearnSetByLevelingUpPoList.size() > 1);
    }
}
