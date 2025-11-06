package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailBaseStatPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * PokemonDetailBaseStatDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class PokemonDetailBaseStatDaoTest extends SpringTest {
    @Resource
    private PokemonDetailBaseStatDao pokemonDetailBaseStatDao;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailBaseStatPo> pokemonBaseStatList = pokemonDetailBaseStatDao.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonBaseStatList);
        Assertions.assertEquals(2, pokemonBaseStatList.size());

        PokemonDetailBaseStatPo bulbasaur = pokemonBaseStatList.getFirst();
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
