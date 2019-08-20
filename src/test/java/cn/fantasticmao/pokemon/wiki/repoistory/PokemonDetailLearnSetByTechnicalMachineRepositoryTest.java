package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonDetailLearnSetByTechnicalMachine;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * PokemonDetailLearnSetByTechnicalMachineRepositoryTest
 *
 * @author maomao
 * @since 2019-08-20
 */
public class PokemonDetailLearnSetByTechnicalMachineRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailLearnSetByTechnicalMachineRepository pokemonDetailLearnSetByTechnicalMachineRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailLearnSetByTechnicalMachine> pokemonDetailLearnSetByTechnicalMachineList
                = pokemonDetailLearnSetByTechnicalMachineRepository.findByIndexIn(Collections.singletonList(1));
        Assert.assertNotNull(pokemonDetailLearnSetByTechnicalMachineList);
        System.out.println(pokemonDetailLearnSetByTechnicalMachineList);
    }
}