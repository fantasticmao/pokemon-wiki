package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * PokemonComplexRepositoryTest
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
public class PokemonComplexRepositoryTest extends SpringTest {
    @Resource
    private PokemonComplexRepository pokemonComplexRepository;

    @Test
    public void findByGeneration() {
        List<Pokemon> pokemonList = pokemonComplexRepository.listByGenerationAndEggGroup(1, null, null, 50);
        Assertions.assertNotNull(pokemonList);

        Integer maxIndex = pokemonList.stream()
            .map(Pokemon::getIdx)
            .max(Integer::compareTo)
            .orElse(0);
        Assertions.assertEquals(151, maxIndex);

        Pokemon mew = pokemonList.get(pokemonList.size() - 1);
        Assertions.assertEquals(151, mew.getIdx());
        Assertions.assertEquals("梦幻", mew.getNameZh());
        Assertions.assertEquals("ミュウ", mew.getNameJa());
        Assertions.assertEquals("Mew", mew.getNameEn());
        Assertions.assertEquals("超能力", mew.getType1());
        Assertions.assertEquals("", mew.getType2());
        Assertions.assertEquals("", mew.getForm());
        Assertions.assertEquals(1, mew.getGeneration());
    }

    @Test
    public void findByEggGroup() {
        List<Pokemon> pokemonList = pokemonComplexRepository.listByGenerationAndEggGroup(null, "百变怪", null, 50);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(1, pokemonList.size());
    }

    @Test
    public void findAll() {
        List<Pokemon> pokemonList = pokemonComplexRepository.listByGenerationAndEggGroup(null, null, null, 50);
        Assertions.assertNotNull(pokemonList);

        Integer maxIndex = pokemonList.stream()
            .map(Pokemon::getIdx)
            .max(Integer::compareTo)
            .orElse(0);
        Assertions.assertEquals(1008, maxIndex);
    }

    @Test
    public void findByGenerationAndEggGroup() {
        List<Pokemon> pokemonList = pokemonComplexRepository.listByGenerationAndEggGroup(1, "龙", null, 50);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(12, pokemonList.size());
    }


    @Test
    public void findByGenerationAndEggGroupWithPage() {
        List<Pokemon> pokemonList = pokemonComplexRepository.listByGenerationAndEggGroup(1, "龙", 0, 20);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(12, pokemonList.size());
    }
}
