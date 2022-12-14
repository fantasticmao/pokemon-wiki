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
 * @author fantasticmao
 * @since 2021-10-31
 */
public class PokemonServiceTest extends SpringTest {
    @Resource
    private PokemonService pokemonService;

    @Test
    public void listByIndex() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(1, null, null);
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(1, pokemonBeanList.size());
        super.assertBulbasaur(pokemonBeanList.get(0));
    }

    @Test
    public void listByNameZh() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(null, "妙蛙", null);
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(3, pokemonBeanList.size());
        super.assertBulbasaur(pokemonBeanList.get(0));
    }

    @Test
    public void listByNameZhWithForm() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(null, "小拉达", null);
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(2, pokemonBeanList.size());

        pokemonBeanList = pokemonService.listByIndexOrNameZh(null, "小拉达", "阿罗拉的样子");
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(1, pokemonBeanList.size());
    }

    @Test
    public void listByGeneration() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(1, null, null, 50);
        Assertions.assertNotNull(pokemonBeanList);

        Integer maxIndex = pokemonBeanList.stream()
            .map(PokemonBean::getIndex)
            .max(Integer::compareTo)
            .orElse(0);
        Assertions.assertEquals(151, maxIndex);
    }

    @Test
    public void findByEggGroup() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(null, "百变怪", null, 50);
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(1, pokemonBeanList.size());
    }

    @Test
    public void listByGenerationAndEggGroup() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(1, "龙", null, 50);
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(12, pokemonBeanList.size());
    }

    @Test
    public void listByGenerationAndEggGroupWithPage() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(1, "龙", 0, 20);
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(12, pokemonBeanList.size());
    }

}
