package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailLearnSetByBreeding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * PokemonDetailLearnSetByBreedingRepositoryTest
 *
 * @author maomao
 * @since 2019-08-20
 */
public class PokemonDetailLearnSetByBreedingRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailLearnSetByBreedingRepository pokemonDetailLearnSetByBreedingRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailLearnSetByBreeding> pokemonDetailLearnSetByBreedingList
            = pokemonDetailLearnSetByBreedingRepository.findByIndexIn(Collections.singletonList(1));
        Assertions.assertNotNull(pokemonDetailLearnSetByBreedingList);
        System.out.println(pokemonDetailLearnSetByBreedingList);
    }

}