package cn.fantasticmao.pokemon.web.infra.dao;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.infra.model.PokemonPo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * PokemonDaoTest
 *
 * @author fantasticmao
 * @since 2018/8/5
 */
public class PokemonDaoTest extends SpringTest {
    @Resource
    private PokemonDao pokemonDao;

    @Test
    public void findByIndex() {
        List<PokemonPo> pokemonPoList = pokemonDao.findByIndexAndForm(1, null);
        Assertions.assertNotNull(pokemonPoList);
        Assertions.assertEquals(1, pokemonPoList.size());

        PokemonPo bulbasaur = pokemonPoList.getFirst();
        Assertions.assertEquals(1, bulbasaur.getIdx());
        Assertions.assertEquals("妙蛙种子", bulbasaur.getNameZh());
        Assertions.assertEquals("フシギダネ", bulbasaur.getNameJa());
        Assertions.assertEquals("Bulbasaur", bulbasaur.getNameEn());
        Assertions.assertEquals("草", bulbasaur.getType1());
        Assertions.assertEquals("毒", bulbasaur.getType2());
        Assertions.assertEquals("", bulbasaur.getForm());
        Assertions.assertEquals(1, bulbasaur.getGeneration());
    }

    @Test
    public void findByNameZh() {
        List<PokemonPo> pokemonPoList = pokemonDao.findByNameAndForm("妙蛙", null, null);
        Assertions.assertNotNull(pokemonPoList);
        Assertions.assertEquals(3, pokemonPoList.size());
    }

    @Test
    public void findByNameEn() {
        List<PokemonPo> pokemonPoList = pokemonDao.findByNameAndForm(null, "Bulba", null);
        Assertions.assertNotNull(pokemonPoList);
        Assertions.assertEquals(1, pokemonPoList.size());
    }

    @Test
    public void findByName() {
        List<PokemonPo> pokemonPoList = pokemonDao.findByNameAndForm("妙蛙", "Bulba", null);
        Assertions.assertNotNull(pokemonPoList);
        Assertions.assertEquals(1, pokemonPoList.size());
    }

    @Test
    public void findByGeneration() {
        List<PokemonPo> pokemonPoList = pokemonDao.findByGenerationAndEggGroup(1, null, null, 50);
        Assertions.assertNotNull(pokemonPoList);

        Integer maxIndex = pokemonPoList.stream()
            .map(PokemonPo::getIdx)
            .max(Integer::compareTo)
            .orElse(0);
        Assertions.assertEquals(151, maxIndex);

        PokemonPo mew = pokemonPoList.getLast();
        Assertions.assertEquals(151, mew.getIdx());
        Assertions.assertEquals("梦幻", mew.getNameZh());
        Assertions.assertEquals("ミュウ", mew.getNameJa());
        Assertions.assertEquals("Mew", mew.getNameEn());
        Assertions.assertEquals("超能力", mew.getType1());
        Assertions.assertEquals("", mew.getType2());
        Assertions.assertEquals("", mew.getForm());
        Assertions.assertEquals(1, mew.getGeneration());
    }

    @Test
    public void findByGenerationAndEggGroup() {
        List<PokemonPo> pokemonPoList = pokemonDao.findByGenerationAndEggGroup(1, "龙", null, 50);
        Assertions.assertNotNull(pokemonPoList);
        Assertions.assertEquals(12, pokemonPoList.size());
    }


    @Test
    public void findByGenerationAndEggGroupWithPage() {
        List<PokemonPo> pokemonPoList = pokemonDao.findByGenerationAndEggGroup(1, "龙", 0, 20);
        Assertions.assertNotNull(pokemonPoList);
        Assertions.assertEquals(12, pokemonPoList.size());
    }
}
