package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.pokemon.model.Pokemon;
import cn.fantasticmao.pokemon.web.domain.pokemon.service.PokemonDomainService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * PokemonDomainServiceTest
 *
 * @author fantasticmao
 * @since 2021-10-31
 */
public class PokemonDomainServiceTest extends SpringTest {
    @Resource
    private PokemonDomainService pokemonDomainService;

    @Test
    public void listByIndex() {
        List<Pokemon> pokemonList = pokemonDomainService.listByIndexOrName(1, null, null, null);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(1, pokemonList.size());
        super.assertBulbasaur(pokemonList.getFirst());
    }

    @Test
    public void listByNameZh() {
        List<Pokemon> pokemonList = pokemonDomainService.listByIndexOrName(null, "妙蛙", null, null);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(3, pokemonList.size());
        super.assertBulbasaur(pokemonList.getFirst());
    }

    @Test
    public void listByNameZhWithForm() {
        List<Pokemon> pokemonList = pokemonDomainService.listByIndexOrName(null, "小拉达", null, null);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(2, pokemonList.size());

        pokemonList = pokemonDomainService.listByIndexOrName(null, "小拉达", null, "阿罗拉的样子");
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(1, pokemonList.size());
    }

    @Test
    public void listByNameEn() {
        List<Pokemon> pokemonList = pokemonDomainService.listByIndexOrName(null, "妙蛙", "Bulba", null);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(1, pokemonList.size());
        super.assertBulbasaur(pokemonList.getFirst());
    }

    @Test
    public void listByNameEnWithForm() {
        List<Pokemon> pokemonList = pokemonDomainService.listByIndexOrName(null, "小拉达", "Rattata", null);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(2, pokemonList.size());

        pokemonList = pokemonDomainService.listByIndexOrName(null, "小拉达", "Rattata", "阿罗拉的样子");
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(1, pokemonList.size());
    }

    @Test
    public void listByGeneration() {
        List<Pokemon> pokemonList = pokemonDomainService.listByGenerationAndEggGroup(1, null, null, 50);
        Assertions.assertNotNull(pokemonList);

        Integer maxIndex = pokemonList.stream()
            .map(Pokemon::getIndex)
            .max(Integer::compareTo)
            .orElse(0);
        Assertions.assertEquals(151, maxIndex);
    }

    @Test
    public void listByGenerationAndEggGroup() {
        List<Pokemon> pokemonList = pokemonDomainService.listByGenerationAndEggGroup(1, "龙", null, 50);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(12, pokemonList.size());
    }

    @Test
    public void listByGenerationAndEggGroupWithPage() {
        List<Pokemon> pokemonList = pokemonDomainService.listByGenerationAndEggGroup(1, "龙", 0, 20);
        Assertions.assertNotNull(pokemonList);
        Assertions.assertEquals(12, pokemonList.size());
    }

}
