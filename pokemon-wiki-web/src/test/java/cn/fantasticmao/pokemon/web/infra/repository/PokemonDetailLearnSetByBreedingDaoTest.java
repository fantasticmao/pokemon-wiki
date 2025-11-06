package cn.fantasticmao.pokemon.web.infra.repository;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDetailLearnSetByBreedingDao;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByBreedingPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/**
 * PokemonDetailLearnSetByBreedingDaoTest
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public class PokemonDetailLearnSetByBreedingDaoTest extends SpringTest {
    @Resource
    private PokemonDetailLearnSetByBreedingDao pokemonDetailLearnSetByBreedingDao;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailLearnSetByBreedingPo> pokemonDetailLearnSetByBreedingPoList =
            pokemonDetailLearnSetByBreedingDao.findByIndexIn(Collections.singletonList(1));
        Assertions.assertNotNull(pokemonDetailLearnSetByBreedingPoList);
        Assertions.assertTrue(pokemonDetailLearnSetByBreedingPoList.size() > 1);
    }

}
