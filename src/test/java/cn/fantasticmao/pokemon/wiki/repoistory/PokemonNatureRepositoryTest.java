package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonNature;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * PokemonNatureRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class PokemonNatureRepositoryTest extends SpringTest {
    @Resource
    private PokemonNatureRepository pokemonNatureRepository;

    @Test
    public void findOne() {
        PokemonNature pokemonNature = pokemonNatureRepository.findOne(1);
        System.out.println(pokemonNature);
    }
}