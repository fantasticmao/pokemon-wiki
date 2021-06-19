package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonDetailLearnSetByLevelingUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * PokemonDetailLearnSetByLevelingUpRepositoryTest
 *
 * @author maomao
 * @since 2019-08-20
 */
public class PokemonDetailLearnSetByLevelingUpRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailLearnSetByLevelingUpRepository pokemonDetailLearnSetByLevelingUpRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailLearnSetByLevelingUp> pokemonDetailLearnSetByLevelingUpList
            = pokemonDetailLearnSetByLevelingUpRepository.findByIndexIn(Collections.singletonList(1));
        Assertions.assertNotNull(pokemonDetailLearnSetByLevelingUpList);
        System.out.println(pokemonDetailLearnSetByLevelingUpList);
    }
}