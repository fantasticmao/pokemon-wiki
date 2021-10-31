package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailBaseStat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * PokemonBaseStatRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class PokemonBaseStatRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailBaseStatRepository pokemonDetailBaseStatRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailBaseStat> pokemonBaseStatList = pokemonDetailBaseStatRepository.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonBaseStatList);
        Assertions.assertEquals(2, pokemonBaseStatList.size());
    }
}