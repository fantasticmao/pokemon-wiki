package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.PokemonAbilityPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * PokemonAbilityDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class PokemonAbilityDaoTest extends SpringTest {
    @Resource
    private PokemonAbilityDao pokemonAbilityDao;

    @Test
    public void findByIndexIn() {
        List<PokemonAbilityPo> pokemonAbilityPoList = pokemonAbilityDao.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonAbilityPoList);
        Assertions.assertEquals(2, pokemonAbilityPoList.size());

        PokemonAbilityPo bulbasaur = pokemonAbilityPoList.getFirst();
        Assertions.assertEquals(1, bulbasaur.getIdx());
        Assertions.assertEquals("妙蛙种子", bulbasaur.getNameZh());
        Assertions.assertEquals("草", bulbasaur.getType1());
        Assertions.assertEquals("毒", bulbasaur.getType2());
        Assertions.assertEquals("茂盛", bulbasaur.getAbility1());
        Assertions.assertEquals("", bulbasaur.getAbility2());
        Assertions.assertEquals("叶绿素", bulbasaur.getAbilityHide());
        Assertions.assertEquals("", bulbasaur.getForm());
        Assertions.assertEquals(1, bulbasaur.getGeneration());
    }
}
