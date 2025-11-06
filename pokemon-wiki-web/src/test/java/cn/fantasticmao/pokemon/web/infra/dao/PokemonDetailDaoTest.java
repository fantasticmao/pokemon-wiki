package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.PokemonDetailPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * PokemonDetailDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/29
 */
public class PokemonDetailDaoTest extends SpringTest {
    @Resource
    private PokemonDetailDao pokemonDetailDao;

    @Test
    public void findByIndexIn() {
        List<PokemonDetailPo> pokemonDetailPoList = pokemonDetailDao.findByIndexIn(Arrays.asList(1, 2));
        Assertions.assertNotNull(pokemonDetailPoList);
        Assertions.assertEquals(2, pokemonDetailPoList.size());

        PokemonDetailPo bulbasaur = pokemonDetailPoList.getFirst();
        Assertions.assertEquals(1, bulbasaur.getIdx());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/2/21/001Bulbasaur.png/300px-001Bulbasaur.png", bulbasaur.getImgUrl());
        Assertions.assertEquals("草,毒", bulbasaur.getType());
        Assertions.assertEquals("种子宝可梦", bulbasaur.getCategory());
        Assertions.assertEquals("茂盛,叶绿素（隐藏特性）", bulbasaur.getAbility());
        Assertions.assertEquals("0.7m", bulbasaur.getHeight());
        Assertions.assertEquals("6.9kg", bulbasaur.getWeight());
        Assertions.assertEquals("https://s1.52poke.wiki/wiki/thumb/c/cc/Body08.png/32px-Body08.png", bulbasaur.getBodyStyle());
        Assertions.assertEquals("5.9%", bulbasaur.getCatchRate());
        Assertions.assertEquals("雄性 87.5%,雌性 12.5%", bulbasaur.getGenderRatio());
        Assertions.assertEquals("怪兽", bulbasaur.getEggGroup1());
        Assertions.assertEquals("植物", bulbasaur.getEggGroup2());
        Assertions.assertEquals("5140步", bulbasaur.getHatchTime());
        Assertions.assertEquals("ＨＰ 0,攻击 0,防御 0,特攻 1,特防 0,速度 0", bulbasaur.getEffortValue());
    }

}
