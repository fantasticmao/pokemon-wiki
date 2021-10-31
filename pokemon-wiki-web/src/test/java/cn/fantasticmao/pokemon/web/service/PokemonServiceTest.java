package cn.fantasticmao.pokemon.web.service;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.bean.PokemonBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * PokemonServiceTest
 *
 * @author maomao
 * @since 2021-10-31
 */
public class PokemonServiceTest extends SpringTest {
    @Resource
    private PokemonService pokemonService;

    @Test
    public void listByIndex() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(1, "");
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(1, pokemonBeanList.size());
    }

    @Test
    public void listByNameZh() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(0, "妙蛙");
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(3, pokemonBeanList.size());
    }

    @Test
    public void listByGeneration() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(1, "");
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(153, pokemonBeanList.size());
    }

    @Test
    public void listByGenerationAndEggGroup() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(1, "龙");
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertTrue(pokemonBeanList.size() < 153);
    }

}
