package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByTechnicalMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * PokemonDetailLearnSetByTechnicalMachineRepositoryTest
 *
 * @author fantasticmao
 * @since 2019-08-20
 */
public class PokemonDetailLearnSetByTechnicalMachineRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailLearnSetByTechnicalMachineRepository pokemonDetailLearnSetByTechnicalMachineRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailLearnSetByTechnicalMachine> pokemonDetailLearnSetByTechnicalMachineList
            = pokemonDetailLearnSetByTechnicalMachineRepository.findByIndexIn(Collections.singletonList(1));
        Assertions.assertNotNull(pokemonDetailLearnSetByTechnicalMachineList);
        Assertions.assertTrue(pokemonDetailLearnSetByTechnicalMachineList.size() > 1);
    }
}
