package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
    public void findByNameZh() {
        List<Pokemon> pokemonList = pokemonRepository.findByNameZh("妙蛙");
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(3, pokemonList.size());
    }

    @Test
    public void findByIndex() {
        Optional<Pokemon> pokemonOptional = pokemonRepository.findByIndex(1);
        Assertions.assertTrue(pokemonOptional.isPresent());
    }
}