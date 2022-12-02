package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.PokemonDetailBaseStat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * PokemonBaseStatRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class PokemonBaseStatRepositoryTest extends SpringTest {
    @Resource
    private PokemonDetailBaseStatRepository pokemonDetailBaseStatRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailBaseStat> pokemonBaseStatList = pokemonDetailBaseStatRepository.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonBaseStatList);
        Assertions.assertEquals(2, pokemonBaseStatList.size());

        PokemonDetailBaseStat bulbasaur = pokemonBaseStatList.get(0);
        Assertions.assertEquals(1, bulbasaur.getIdx());
        Assertions.assertEquals(45, bulbasaur.getHp());
        Assertions.assertEquals(49, bulbasaur.getAttack());
        Assertions.assertEquals(49, bulbasaur.getDefense());
        Assertions.assertEquals(65, bulbasaur.getSpAttack());
        Assertions.assertEquals(65, bulbasaur.getSpDefense());
        Assertions.assertEquals(45, bulbasaur.getSpeed());
        Assertions.assertEquals(318, bulbasaur.getTotal());
        Assertions.assertEquals(53F, bulbasaur.getAverage());
    }
}
