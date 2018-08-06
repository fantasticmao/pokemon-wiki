package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.Pokemon;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

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
    public void findOne() {
        Pokemon pokemon = pokemonRepository.findOne(1);
        System.out.println(pokemon);
    }

    @Test
    public void findByName() {
        List<Pokemon> pokemonList = pokemonRepository.findByName("龙");
        System.out.println(pokemonList);
    }
}