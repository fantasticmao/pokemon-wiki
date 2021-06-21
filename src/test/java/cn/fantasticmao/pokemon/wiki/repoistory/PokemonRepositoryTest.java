package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * PokemonRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class PokemonRepositoryTest extends SpringTest {
    @Resource
    private PokemonRepository pokemonRepository;

    @Test
    public void findByNameZh() {
        List<Pokemon> pokemonList = pokemonRepository.findByNameZh("妙蛙种子");
        Assertions.assertNotNull(pokemonList);
        System.out.println(pokemonList);
    }

    @Test
    public void findByIndex() {
        Optional<Pokemon> pokemonOptional = pokemonRepository.findByIndex(1);
        Assertions.assertTrue(pokemonOptional.isPresent());
        System.out.println(pokemonOptional);
    }
}