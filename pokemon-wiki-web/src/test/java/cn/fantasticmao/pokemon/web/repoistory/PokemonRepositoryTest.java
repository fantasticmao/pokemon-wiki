package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * PokemonRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class PokemonRepositoryTest extends SpringTest {
    @Resource
    private PokemonRepository pokemonRepository;

    @Test
    public void findAll() {
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        Assertions.assertNotNull(pokemonList);

        Integer maxIndex = pokemonList.stream()
            .map(Pokemon::getIdx)
            .max(Integer::compareTo)
            .orElse(0);
        Assertions.assertEquals(1008, maxIndex);
    }

    @Test
    public void findByNameZh() {
        List<Pokemon> pokemonList = pokemonRepository.findByNameZh("妙蛙");
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(3, pokemonList.size());
    }

    @Test
    public void findByIndex() {
        List<Pokemon> pokemonList = pokemonRepository.findByIndex(1);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(1, pokemonList.size());

        Pokemon bulbasaur = pokemonList.get(0);
        Assertions.assertEquals(1, bulbasaur.getIdx());
        Assertions.assertEquals("妙蛙种子", bulbasaur.getNameZh());
        Assertions.assertEquals("フシギダネ", bulbasaur.getNameJa());
        Assertions.assertEquals("Bulbasaur", bulbasaur.getNameEn());
        Assertions.assertEquals("草", bulbasaur.getType1());
        Assertions.assertEquals("毒", bulbasaur.getType2());
        Assertions.assertEquals("", bulbasaur.getForm());
        Assertions.assertEquals(1, bulbasaur.getGeneration());
    }

    @Test
    public void findByGeneration() {
        List<Pokemon> pokemonList = pokemonRepository.findByGeneration(1);
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
}
