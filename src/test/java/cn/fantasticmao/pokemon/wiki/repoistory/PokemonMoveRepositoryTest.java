package cn.fantasticmao.pokemon.wiki.repoistory;

import cn.fantasticmao.pokemon.SpringTest;
import cn.fantasticmao.pokemon.wiki.domain.PokemonMove;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * PokemonMoveRepositoryTest
 *
 * @author maodh
 * @since 2018/8/5
 */
public class PokemonMoveRepositoryTest extends SpringTest {
    @Resource
    private PokemonMoveRepository pokemonMoveRepository;

    @Test
    public void findOne() {
        PokemonMove pokemonMove = pokemonMoveRepository.findOne(1);
        System.out.println(pokemonMove);
    }
}