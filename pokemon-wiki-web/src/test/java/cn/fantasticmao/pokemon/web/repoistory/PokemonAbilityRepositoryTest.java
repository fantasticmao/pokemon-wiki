package cn.fantasticmao.pokemon.web.repoistory;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.PokemonAbility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * PokemonAbilityRepositoryTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class PokemonAbilityRepositoryTest extends SpringTest {
    @Resource
    private PokemonAbilityRepository pokemonAbilityRepository;

    @Test
    public void findByIndexIn() {
        List<PokemonAbility> pokemonAbilityList = pokemonAbilityRepository.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonAbilityList);
        Assertions.assertEquals(2, pokemonAbilityList.size());

        PokemonAbility bulbasaur = pokemonAbilityList.get(0);
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
