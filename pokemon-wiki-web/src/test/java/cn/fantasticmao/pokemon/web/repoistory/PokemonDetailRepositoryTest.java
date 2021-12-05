package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.PokemonDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * PokemonDetailRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class PokemonDetailRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailRepository pokemonDetailRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonDetail> pokemonDetailList = pokemonDetailRepository.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonDetailList);
        Assertions.assertEquals(2, pokemonDetailList.size());
    }

}