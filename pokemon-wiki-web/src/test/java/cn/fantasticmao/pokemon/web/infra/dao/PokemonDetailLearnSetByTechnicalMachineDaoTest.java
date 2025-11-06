package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailLearnSetByTechnicalMachinePo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/**
 * PokemonDetailLearnSetByTechnicalMachineDaoTest
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public class PokemonDetailLearnSetByTechnicalMachineDaoTest extends SpringTest {
    @Resource
    private PokemonDetailLearnSetByTechnicalMachineDao pokemonDetailLearnSetByTechnicalMachineDao;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailLearnSetByTechnicalMachinePo> pokemonDetailLearnSetByTechnicalMachinePoList =
            pokemonDetailLearnSetByTechnicalMachineDao.findByIndexIn(Collections.singletonList(1));
        Assertions.assertNotNull(pokemonDetailLearnSetByTechnicalMachinePoList);
        Assertions.assertTrue(pokemonDetailLearnSetByTechnicalMachinePoList.size() > 1);
    }
}
