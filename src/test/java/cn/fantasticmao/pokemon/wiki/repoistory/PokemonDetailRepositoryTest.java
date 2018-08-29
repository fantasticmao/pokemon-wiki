package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonDetail;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * PokemonDetailRepositoryTest
 *
 * @author maodh
 * @since 2018/8/29
 */
public class PokemonDetailRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailRepository pokemonDetailRepository;

    @Test
    public void findOne() {
        PokemonDetail pokemonDetail = pokemonDetailRepository.findOne(1);
        Assert.assertNotNull(pokemonDetail);
    }

}