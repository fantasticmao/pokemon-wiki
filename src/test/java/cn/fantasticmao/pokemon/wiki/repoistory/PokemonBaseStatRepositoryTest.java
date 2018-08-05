package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonBaseStat;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * PokemonBaseStatRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class PokemonBaseStatRepositoryTest extends SpringTest {
    @Resource
    private PokemonBaseStatRepository pokemonBaseStatRepository;

    @Test
    public void findOne() {
        PokemonBaseStat pokemonBaseStat = pokemonBaseStatRepository.findOne(1);
        System.out.println(pokemonBaseStat);
    }
}