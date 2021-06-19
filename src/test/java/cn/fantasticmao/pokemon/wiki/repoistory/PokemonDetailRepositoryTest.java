package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
    public void findByIndexIn() {
        List<PokemonDetail> pokemonDetailList = pokemonDetailRepository.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonDetailList);
        System.out.println(pokemonDetailList);
    }

}