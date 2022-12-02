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
        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(1, "");
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(1, pokemonBeanList.size());
        assertBulbasaur(pokemonBeanList.get(0));
    }

    @Test
    public void listByNameZh() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByIndexOrNameZh(0, "妙蛙");
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(3, pokemonBeanList.size());
        assertBulbasaur(pokemonBeanList.get(0));
    }

    @Test
    public void listByGeneration() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(1, "", 0, 20);
        Assertions.assertNotNull(pokemonBeanList);

        Integer maxIndex = pokemonBeanList.stream()
            .map(PokemonBean::getIndex)
            .max(Integer::compareTo)
            .orElse(0);
        Assertions.assertEquals(151, maxIndex);
    }

    @Test
    public void listByGenerationAndEggGroup() {
        List<PokemonBean> pokemonBeanList = pokemonService.listByGenerationAndEggGroup(1, "龙", 0, 20);
        Assertions.assertNotNull(pokemonBeanList);
        Assertions.assertEquals(12, pokemonBeanList.size());
    }

    private void assertBulbasaur(PokemonBean bulbasaur) {
        Assertions.assertEquals(1, bulbasaur.getIndex());
        Assertions.assertEquals("妙蛙种子", bulbasaur.getNameZh());
        Assertions.assertEquals("フシギダネ", bulbasaur.getNameJa());
        Assertions.assertEquals("Bulbasaur", bulbasaur.getNameEn());
        Assertions.assertEquals("草", bulbasaur.getType1());
        Assertions.assertEquals("毒", bulbasaur.getType2());
        Assertions.assertEquals("茂盛", bulbasaur.getAbility1());
        Assertions.assertEquals("", bulbasaur.getAbility2());
        Assertions.assertEquals("叶绿素", bulbasaur.getAbilityHide());
        Assertions.assertEquals("", bulbasaur.getForm());
        Assertions.assertEquals(1, bulbasaur.getGeneration());

        Assertions.assertEquals(45, bulbasaur.getBaseStat().getHp());
        Assertions.assertEquals(49, bulbasaur.getBaseStat().getAttack());
        Assertions.assertEquals(49, bulbasaur.getBaseStat().getDefense());
        Assertions.assertEquals(65, bulbasaur.getBaseStat().getSpAttack());
        Assertions.assertEquals(65, bulbasaur.getBaseStat().getSpDefense());
        Assertions.assertEquals(45, bulbasaur.getBaseStat().getSpeed());
        Assertions.assertEquals(318, bulbasaur.getBaseStat().getTotal());
        Assertions.assertEquals(53F, bulbasaur.getBaseStat().getAverage());

        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/2/21/001Bulbasaur.png/300px-001Bulbasaur.png", bulbasaur.getDetail().getImgUrl());
        Assertions.assertEquals("种子宝可梦", bulbasaur.getDetail().getCategory());
        Assertions.assertEquals("0.7m", bulbasaur.getDetail().getHeight());
        Assertions.assertEquals("6.9kg", bulbasaur.getDetail().getWeight());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/c/cc/Body08.png/32px-Body08.png", bulbasaur.getDetail().getBodyStyle());
        Assertions.assertEquals("5.9%", bulbasaur.getDetail().getCatchRate());
        Assertions.assertEquals("雄性 87.5%,雌性 12.5%", bulbasaur.getDetail().getGenderRatio());
        Assertions.assertEquals("怪兽", bulbasaur.getDetail().getEggGroup1());
        Assertions.assertEquals("植物", bulbasaur.getDetail().getEggGroup2());
        Assertions.assertEquals("5140步", bulbasaur.getDetail().getHatchTime());
        Assertions.assertEquals("ＨＰ 0,攻击 0,防御 0,特攻 1,特防 0,速度 0", bulbasaur.getDetail().getEffortValue());
    }
}
